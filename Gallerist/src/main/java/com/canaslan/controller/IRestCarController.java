package com.canaslan.controller;

import com.canaslan.dto.DtoCar;
import com.canaslan.dto.DtoCarIU;

public interface IRestCarController {
	
	RootEntity<DtoCar> saveCar(DtoCarIU input);
	
	public RootEntity<DtoCar> updateCar(Long id, DtoCarIU input);
	
	public String deleteCar(Long id);
	
}
