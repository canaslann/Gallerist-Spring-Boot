package com.canaslan.service;

import com.canaslan.dto.DtoGallerist;
import com.canaslan.dto.DtoGalleristIU;

public interface IGallleristService {
	
	public DtoGallerist saveGallerist(DtoGalleristIU input);
	
	public String deleteGallerist(Long id);
	
	public DtoGallerist updateGallerist(Long id, DtoGalleristIU input);
}
