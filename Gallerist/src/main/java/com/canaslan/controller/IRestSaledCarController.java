package com.canaslan.controller;

import com.canaslan.dto.DtoSaledCar;
import com.canaslan.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
	
	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU input);

}
