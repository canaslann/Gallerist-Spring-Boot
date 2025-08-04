package com.canaslan.controller;

import com.canaslan.dto.DtoAccount;
import com.canaslan.dto.DtoAccountIU;

public interface IRestAccountController {
	
	public RootEntity<DtoAccount> saveAccount(DtoAccountIU accountIU);
	
	public void deleteAccount(Long accountId);
	
	public RootEntity<DtoAccount> updateAccount(Long id, DtoAccountIU input);
	
}
