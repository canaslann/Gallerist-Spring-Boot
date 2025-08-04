package com.canaslan.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canaslan.dto.DtoAccount;
import com.canaslan.dto.DtoAddress;
import com.canaslan.dto.DtoCustomer;
import com.canaslan.dto.DtoCustomerIU;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Account;
import com.canaslan.model.Address;
import com.canaslan.model.Customer;
import com.canaslan.repository.AccountRepository;
import com.canaslan.repository.AddressRepository;
import com.canaslan.repository.CustomerRepository;
import com.canaslan.service.ICustomerService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	private Customer createCustomer(DtoCustomerIU dtoCustomer) {
		
		Optional<Address> addressOptional = addressRepository.findById(dtoCustomer.getAddressId());
		if (addressOptional.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoCustomer.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Account> accountOptional = accountRepository.findById(dtoCustomer.getAccountId());
		if (accountOptional.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoCustomer.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCustomer, customer);
		
		customer.setAccount(accountOptional.get());
		customer.setAddress(addressOptional.get());
		
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
		
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		
		dtoCustomer.setAccount(dtoAccount);
		dtoCustomer.setAddress(dtoAddress);
		
		return dtoCustomer;
	}

	@Override
	public String deleteCustomer(Long id) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if (optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		customerRepository.delete(optCustomer.get());
		
		return "Entity was success delete";
	}

	@Override
	public DtoCustomer updateCustomer(Long id, DtoCustomerIU input) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if (optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Account> optAccount = accountRepository.findById(input.getAccountId());
		if (optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Address> optAddress = addressRepository.findById(input.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		optCustomer.get().setAccount(optAccount.get());
		optCustomer.get().setAddress(optAddress.get());
		optCustomer.get().setDateOfBirth(input.getDateOfBirth());
		optCustomer.get().setFirstName(input.getFirstName());
		optCustomer.get().setLastName(input.getLastName());
		optCustomer.get().setTckn(input.getTckn());
		
		customerRepository.save(optCustomer.get());
		
		DtoCustomer dtoCustomer = new DtoCustomer();
		BeanUtils.copyProperties(optCustomer, dtoCustomer);
		
		return dtoCustomer;
	}
	
}
