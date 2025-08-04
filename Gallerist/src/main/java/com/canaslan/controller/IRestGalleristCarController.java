package com.canaslan.controller;

import com.canaslan.dto.DtoGalleristCar;
import com.canaslan.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {
	 public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU input);
	 
	 public String deleteGalleristCar(Long id);
	 
	 public RootEntity<DtoGalleristCar> updateGalleristCar(Long id, DtoGalleristCarIU input);
}
