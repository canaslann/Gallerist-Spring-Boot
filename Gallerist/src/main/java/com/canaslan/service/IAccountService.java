package com.canaslan.service;

import com.canaslan.dto.DtoAccount;
import com.canaslan.dto.DtoAccountIU;

public interface IAccountService {
	
	public DtoAccount saveAccount(DtoAccountIU accountIU);
	
	public void deleteAccount(Long accountId);
	
	public DtoAccount updateAccount(Long accountId, DtoAccountIU input);
	
}
