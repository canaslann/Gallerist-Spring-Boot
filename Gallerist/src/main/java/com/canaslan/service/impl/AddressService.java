package com.canaslan.service.impl;

import com.canaslan.dto.DtoAddress;
import com.canaslan.dto.DtoAddressIU;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Address;
import com.canaslan.repository.AddressRepository;
import com.canaslan.service.IAddressService;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	private Address createAddress(DtoAddressIU addressIU) {
		
		Address address = new Address();
		address.setCreateTime(new Date());
		
		BeanUtils.copyProperties(addressIU, address);
		
		return address;
				
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressIU addressIU) {
		DtoAddress dtoAddress = new DtoAddress();
		
		Address saveAddress = addressRepository.save(createAddress(addressIU));
		
		BeanUtils.copyProperties(saveAddress, dtoAddress);
		
		return dtoAddress;
	}

	@Override
	public String deleteAddress(Long addressId) {
		
		Optional<Address> optAddress = addressRepository.findById(addressId);
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(addressId.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		addressRepository.delete(optAddress.get());
		
		return "Entity was success delete";
		
	}

	@Override
	public DtoAddress updateAddress(Long addressId, DtoAddressIU input) {
		Optional<Address> optAddress = addressRepository.findById(addressId);
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(addressId.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		optAddress.get().setCity(input.getCity());
		optAddress.get().setDistrict(input.getDistrict());
		optAddress.get().setNeighborhood(input.getNeighborhood());
		optAddress.get().setStreet(input.getStreet());
		
		addressRepository.save(optAddress.get());
		
		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(optAddress.get(), dtoAddress);		
		
		return dtoAddress;
	}
	
	 

}
