package com.canaslan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaslan.controller.IRestSaledCarController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.DtoSaledCar;
import com.canaslan.dto.DtoSaledCarIU;
import com.canaslan.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarController extends RestBaseController implements IRestSaledCarController{
	
	@Autowired
	private ISaledCarService saledCarService;
	  
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU input) {

		return ok(saledCarService.buyCar(input));
	}

}
