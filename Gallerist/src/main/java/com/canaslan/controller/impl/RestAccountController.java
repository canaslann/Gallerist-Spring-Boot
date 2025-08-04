package com.canaslan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaslan.controller.IRestAccountController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.DtoAccount;
import com.canaslan.dto.DtoAccountIU;
import com.canaslan.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController extends RestBaseController implements IRestAccountController{
	
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU accountIU) {
		
		return ok(accountService.saveAccount(accountIU));
	}
	
	@DeleteMapping("/delete/{accountId}")
	@Override
	public void deleteAccount(@PathVariable Long accountId) {
		accountService.deleteAccount(accountId);
	}
	
	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@PathVariable Long id,@Valid @RequestBody DtoAccountIU input) {

		return ok(accountService.updateAccount(id, input));
	}

}
