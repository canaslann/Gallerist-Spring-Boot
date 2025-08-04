package com.canaslan.service;

import com.canaslan.dto.DtoCar;
import com.canaslan.dto.DtoCarIU;

public interface ICarService {
	
	public DtoCar saveCar(DtoCarIU input);
	
	public String deleteCar(Long id);
	
	public DtoCar updateCar(Long id, DtoCarIU input);

}
