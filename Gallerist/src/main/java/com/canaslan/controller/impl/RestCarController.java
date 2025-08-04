package com.canaslan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaslan.controller.IRestCarController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.DtoCar;
import com.canaslan.dto.DtoCarIU;
import com.canaslan.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarController extends RestBaseController implements IRestCarController{
	
	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@RequestBody @Valid DtoCarIU input) {
		return ok(carService.saveCar(input));
	}
	
	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@PathVariable Long id, @Valid @RequestBody DtoCarIU input) {
		
		return ok(carService.updateCar(id, input));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public String deleteCar(@PathVariable Long id) {

		return carService.deleteCar(id);
	}

}
