package com.canaslan.service;

import com.canaslan.dto.DtoAddress;
import com.canaslan.dto.DtoAddressIU;

public interface IAddressService {
	
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public String deleteAddress(Long addressId);
	
	public DtoAddress updateAddress(Long addressId, DtoAddressIU input);
	
}
