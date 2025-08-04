package com.canaslan.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.canaslan.dto.DtoAccount;
import com.canaslan.dto.DtoAccountIU;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Account;
import com.canaslan.repository.AccountRepository;
import com.canaslan.service.IAccountService;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	private Account createAccount(DtoAccountIU accountIU) {
		Account account = new Account();
		account.setCreateTime(new Date());
		
		BeanUtils.copyProperties(accountIU, account);
		
		return account;
	}
	
	@Override
	public DtoAccount saveAccount(DtoAccountIU accountIU) {
		
		DtoAccount dtoAccount = new DtoAccount();
		
		Account saveAccount = accountRepository.save(createAccount(accountIU));
		
		BeanUtils.copyProperties(saveAccount, dtoAccount);
		
		return dtoAccount;
	}

	@Override
	public void deleteAccount(Long accountId) {
		
		Optional<Account> optAccount = accountRepository.findById(accountId);
		
		if (optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(accountId.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		accountRepository.delete(optAccount.get());
		
	}

	@Override
	public DtoAccount updateAccount(Long accountId, DtoAccountIU input) {
		Optional<Account> optAccount = accountRepository.findById(accountId);
		
		if (optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(accountId.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		optAccount.get().setAccountNumber(input.getAccountNumber());
		optAccount.get().setAmount(input.getAmount());
		optAccount.get().setCurrencyType(input.getCurrencyType());
		optAccount.get().setIban(input.getIban());
		accountRepository.save(optAccount.get());
		
		DtoAccount dtoAccount = new DtoAccount();
		dtoAccount.setCreateTime(new Date());
		BeanUtils.copyProperties(optAccount.get(), dtoAccount);
		dtoAccount.setCurrencyType(input.getCurrencyType());
		return dtoAccount;
	}

}
