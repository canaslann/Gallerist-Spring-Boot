package com.canaslan.dto;

import java.math.BigDecimal;

import com.canaslan.enums.CarStatusType;
import com.canaslan.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCarIU {
	
	@NotNull
    private String plaka;
	
	@NotNull
    private String model;
	
	@NotNull
    private String brand;
	
	@NotNull
    private Integer productionYear;
	
	@NotNull
    private BigDecimal price;
	
	@NotNull
    private CurrencyType currencyType;
	
	@NotNull
    private BigDecimal damagePrice;
	
	@NotNull
    private CarStatusType carStatusType;
	
}
