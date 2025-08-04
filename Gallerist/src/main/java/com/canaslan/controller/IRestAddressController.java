package com.canaslan.controller;

import com.canaslan.dto.DtoAddress;
import com.canaslan.dto.DtoAddressIU;

public interface IRestAddressController {
	
	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
	public String deleteAddress(Long addressId);
	
	public RootEntity<DtoAddress> updateAddress(Long id, DtoAddressIU input); 
	
}
