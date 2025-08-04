package com.canaslan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.canaslan.controller.IRestGalleristCarController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.DtoGalleristCar;
import com.canaslan.dto.DtoGalleristCarIU;
import com.canaslan.service.IGalleristCarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/galleristcar")
public class RestGalleristCarController extends RestBaseController implements IRestGalleristCarController{
	
	@Autowired
	private IGalleristCarService galleristCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@RequestBody @Valid DtoGalleristCarIU input) {
		
		return ok(galleristCarService.saveGalleristCar(input));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public String deleteGalleristCar(@PathVariable Long id) {

		return galleristCarService.deleteGalleristCar(id);
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGalleristCar> updateGalleristCar(@PathVariable Long id, @Valid @RequestBody DtoGalleristCarIU input) {

		return ok(galleristCarService.updateGalleristCar(id, input));
	}

}
