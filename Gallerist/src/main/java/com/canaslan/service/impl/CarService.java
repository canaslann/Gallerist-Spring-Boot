package com.canaslan.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canaslan.dto.DtoCar;
import com.canaslan.dto.DtoCarIU;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Car;
import com.canaslan.repository.CarRepository;
import com.canaslan.service.ICarService;

@Service
public class CarService implements ICarService{
	
	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DtoCarIU input) {
		
		Car car = new Car();
		car.setCreateTime(new Date());
		BeanUtils.copyProperties(input, car);
		
		return car;
	}

	@Override
	public DtoCar saveCar(DtoCarIU input) {
		DtoCar dtoCar = new DtoCar();
		Car car = carRepository.save(createCar(input));
		
		BeanUtils.copyProperties(car, dtoCar);	
		
		return dtoCar;
	}

	@Override
	public String deleteCar(Long id) {
		Optional<Car> optCar = carRepository.findById(id);
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		carRepository.delete(optCar.get());
		
		return "Entity was success delete";
	}

	@Override
	public DtoCar updateCar(Long id, DtoCarIU input) {
		Optional<Car> optCar = carRepository.findById(id);
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		optCar.get().setBrand(input.getBrand());
		optCar.get().setCarStatusType(input.getCarStatusType());
		optCar.get().setCurrencyType(input.getCurrencyType());
		optCar.get().setDamagePrice(input.getDamagePrice());
		optCar.get().setModel(input.getModel());
		optCar.get().setPlaka(input.getPlaka());
		optCar.get().setPrice(input.getPrice());
		optCar.get().setProductionYear(input.getProductionYear());
		
		carRepository.save(optCar.get());
		DtoCar dtoCar = new DtoCar(); 
		BeanUtils.copyProperties(optCar.get(), dtoCar);
		
		return dtoCar;
	}

}
