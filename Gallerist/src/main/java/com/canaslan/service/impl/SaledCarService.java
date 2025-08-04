package com.canaslan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canaslan.dto.CurrencyRatesResponse;
import com.canaslan.dto.DtoCar;
import com.canaslan.dto.DtoCustomer;
import com.canaslan.dto.DtoGallerist;
import com.canaslan.dto.DtoSaledCar;
import com.canaslan.dto.DtoSaledCarIU;
import com.canaslan.enums.CarStatusType;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Account;
import com.canaslan.model.Car;
import com.canaslan.model.Customer;
import com.canaslan.model.SaledCar;
import com.canaslan.repository.CarRepository;
import com.canaslan.repository.CustomerRepository;
import com.canaslan.repository.GalleristRepository;
import com.canaslan.repository.SaledCarRepository;
import com.canaslan.service.ICurrencyRatesService;
import com.canaslan.service.ISaledCarService;
import com.canaslan.utils.DateUtils;

@Service
public class SaledCarService implements ISaledCarService{
	
	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.
				getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd()); 
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd,2,RoundingMode.HALF_UP);
		
		return customerUSDAmount;
	}
	
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}
	
	public boolean checkAmount(DtoSaledCarIU input) {
		
		Optional<Customer> optCustomer = customerRepository.findById(input.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getCustomerId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Car> optCar = carRepository.findById(input.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getCarId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		
		if (customerUSDAmount.compareTo(optCar.get().getPrice()) >= 0 ) {
			return true;
		}
		
		return false;
	}
	
	public BigDecimal remainingCustomerAmount(Customer customer, Car car) {
		BigDecimal amountToUSD = convertCustomerAmountToUSD(customer);
		BigDecimal remainingCustomerUSDAmount = amountToUSD.subtract(car.getPrice());
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		
		return remainingCustomerUSDAmount.multiply(usd);
	}
	
	public DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		
		dtoSaledCar.setDtoCustomer(dtoCustomer);
		dtoSaledCar.setDtoGallerist(dtoGallerist);
		dtoSaledCar.setDtoCar(dtoCar);
		
		return dtoSaledCar;
		
	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}
	
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU input) {
		if (!checkAmount(input)) {
			throw new BaseException(new ErrorMessage(input.getCustomerId().toString(), MessageType.AMOUNT_IS_NOT_ENOUGH));
		}
		
		if (!checkCarStatus(input.getCarId())) {
			throw new BaseException(new ErrorMessage(input.getCarId().toString(), MessageType.CAR_IS_SALED));
		} 
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(input));
		
		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
		
		customerRepository.save(customer);
		
		return toDTO(savedSaledCar);
	}

}
