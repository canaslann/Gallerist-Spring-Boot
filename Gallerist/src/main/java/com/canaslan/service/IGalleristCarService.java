package com.canaslan.service;

import com.canaslan.dto.DtoGalleristCar;
import com.canaslan.dto.DtoGalleristCarIU;

public interface IGalleristCarService {
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU input);
	
	 public String deleteGalleristCar(Long id);
	 
	 public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU input);
}
