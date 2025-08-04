package com.canaslan.controller;

import com.canaslan.dto.DtoCustomer;
import com.canaslan.dto.DtoCustomerIU;

public interface IRestCustomerController {
	
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public String deleteCustomer(Long id);
	
	public RootEntity<DtoCustomer> updateCustomer(Long id, DtoCustomerIU input);

}
