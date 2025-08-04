package com.canaslan.service;

import com.canaslan.dto.DtoSaledCar;
import com.canaslan.dto.DtoSaledCarIU;

public interface ISaledCarService {
	
	public DtoSaledCar buyCar(DtoSaledCarIU input);

}
