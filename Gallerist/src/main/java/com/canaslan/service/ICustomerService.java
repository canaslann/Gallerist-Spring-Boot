package com.canaslan.service;

import com.canaslan.dto.DtoCustomer;
import com.canaslan.dto.DtoCustomerIU;

public interface ICustomerService {
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomer);
	
	public String deleteCustomer(Long id);
	
	public DtoCustomer updateCustomer(Long id, DtoCustomerIU input);
}
