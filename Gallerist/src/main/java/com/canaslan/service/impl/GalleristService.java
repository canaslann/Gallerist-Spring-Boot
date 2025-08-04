package com.canaslan.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canaslan.dto.DtoAddress;
import com.canaslan.dto.DtoGallerist;
import com.canaslan.dto.DtoGalleristIU;
import com.canaslan.enums.MessageType;
import com.canaslan.exception.BaseException;
import com.canaslan.exception.ErrorMessage;
import com.canaslan.model.Address;
import com.canaslan.model.Gallerist;
import com.canaslan.repository.AddressRepository;
import com.canaslan.repository.GalleristRepository;
import com.canaslan.service.IGallleristService;

@Service
public class GalleristService implements IGallleristService{
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	private Gallerist createGallerist(DtoGalleristIU input) {
		
		Optional<Address> foundAddress = addressRepository.findById(input.getAddressId());
		if (foundAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getAddressId().toString(), com.canaslan.enums.MessageType.NO_RECORD_EXIST));
		}
		
		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date());
		
		BeanUtils.copyProperties(input, gallerist);
		gallerist.setAddress(foundAddress.get());
		
		return gallerist;
	}
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU input) {
		Gallerist savedGallerist = galleristRepository.save(createGallerist(input));
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
		
		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		
		dtoGallerist.setAddress(dtoAddress);
		
		return dtoGallerist;
	}

	@Override
	public String deleteGallerist(Long id) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(id);
		if (optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		galleristRepository.delete(optGallerist.get());
		
		return "Entity was success delete";
	}

	@Override
	public DtoGallerist updateGallerist(Long id, DtoGalleristIU input) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(id);
		if (optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Optional<Address> optAddress = addressRepository.findById(input.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		optGallerist.get().setAddress(optAddress.get());
		optGallerist.get().setFirstName(input.getFirstName());
		optGallerist.get().setLastName(input.getLastName());
		
		DtoGallerist dtoGallerist = new DtoGallerist();
		BeanUtils.copyProperties(optGallerist, dtoGallerist);
		
		return dtoGallerist;
	}

}
