package com.canaslan.dto;

import java.math.BigDecimal;

import com.canaslan.enums.CarStatusType;
import com.canaslan.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCar extends DtoBase{
	
	private String plaka;

    private String model;

    private String brand;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
    
}
