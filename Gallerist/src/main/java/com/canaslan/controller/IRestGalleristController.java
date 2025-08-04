package com.canaslan.controller;

import com.canaslan.dto.DtoGallerist;
import com.canaslan.dto.DtoGalleristIU;

public interface IRestGalleristController {
	
	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU input);
	
	public String deleteGallerist(Long id);
	
	public RootEntity<DtoGallerist> updateGallerist(Long id, DtoGalleristIU input);
	
}
