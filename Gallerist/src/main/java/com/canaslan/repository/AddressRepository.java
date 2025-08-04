package com.canaslan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canaslan.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
