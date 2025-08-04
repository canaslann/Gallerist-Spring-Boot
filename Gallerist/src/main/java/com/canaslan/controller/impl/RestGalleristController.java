package com.canaslan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaslan.controller.IRestGalleristController;
import com.canaslan.controller.RestBaseController;
import com.canaslan.controller.RootEntity;
import com.canaslan.dto.DtoGallerist;
import com.canaslan.dto.DtoGalleristIU;
import com.canaslan.service.IGallleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController{
	
	@Autowired
	private IGallleristService gallleristService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@RequestBody @Valid DtoGalleristIU input) {
		return ok(gallleristService.saveGallerist(input));
	}
	
	@DeleteMapping("/delete/{id}")
	@Override
	public String deleteGallerist(@PathVariable Long id) {

		return gallleristService.deleteGallerist(id);
	}
	
	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGallerist> updateGallerist(@PathVariable Long id, @Valid @RequestBody DtoGalleristIU input) {

		return ok(gallleristService.updateGallerist(id, input));
	}

}
