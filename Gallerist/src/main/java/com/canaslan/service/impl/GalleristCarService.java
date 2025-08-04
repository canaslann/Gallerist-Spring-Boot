package com.canaslan.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canaslan.dto.DtoAddress;
import com.canaslan.dto.DtoCar;
import com.canaslan.dto.DtoGallerist;
import com.canaslan.dto.DtoGalleristCar;
import com.canaslan.dto.DtoGalleristCarIU;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Car;
import com.canaslan.model.Gallerist;
import com.canaslan.model.GalleristCar;
import com.canaslan.repository.CarRepository;
import com.canaslan.repository.GalleristCarRepository;
import com.canaslan.repository.GalleristRepository;
import com.canaslan.service.IGalleristCarService;

@Service
public class GalleristCarService implements IGalleristCarService{
	
	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar creatGalleristCar(DtoGalleristCarIU input) {
		Optional<Gallerist> optionalGallerist = galleristRepository.findById(input.getGalleristId());
		if (optionalGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getGalleristId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Car> optionalCar = carRepository.findById(input.getCarId());
		if (optionalCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date());
		galleristCar.setCar(optionalCar.get());
		galleristCar.setGallerist(optionalGallerist.get());
		
		return galleristCar;
	}

	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU input) {
		GalleristCar galleristCar = galleristCarRepository.save(creatGalleristCar(input));
		
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoCar dtoCar = new DtoCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
		 
		BeanUtils.copyProperties(galleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
		BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setDtoCar(dtoCar);
		dtoGalleristCar.setDtoGallerist(dtoGallerist);
		return dtoGalleristCar;
	}

	@Override
	public String deleteGalleristCar(Long id) {
		Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
		if (optGalleristCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		galleristCarRepository.delete(optGalleristCar.get());
		
		return "Entity was success delete";
	}

	@Override
	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU input) {
		Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
		if (optGalleristCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Car> optCar = carRepository.findById(input.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Gallerist> optGallerist = galleristRepository.findById(input.getGalleristId());
		if (optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		optGalleristCar.get().setCar(optCar.get());
		optGalleristCar.get().setGallerist(optGallerist.get());
		
		galleristCarRepository.save(optGalleristCar.get());
		
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		BeanUtils.copyProperties(optGalleristCar.get(), dtoGalleristCar);
		
		return dtoGalleristCar;
	}

}
