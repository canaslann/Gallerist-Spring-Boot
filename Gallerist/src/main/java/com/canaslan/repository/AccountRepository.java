package com.canaslan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.canaslan.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
