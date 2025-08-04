package com.canaslan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaslan.controller.IRestCustomerController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.DtoCustomer;
import com.canaslan.dto.DtoCustomerIU;
import com.canaslan.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController extends RestBaseController implements IRestCustomerController{
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public String deleteCustomer(@PathVariable Long id) {
		
		return customerService.deleteCustomer(id);
	}
	
	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCustomer> updateCustomer(@PathVariable Long id, @Valid @RequestBody DtoCustomerIU input) {

		return ok(customerService.updateCustomer(id, input));
	}

}
