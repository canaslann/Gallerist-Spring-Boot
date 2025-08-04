package com.canaslan.dto;

import java.math.BigDecimal;
import com.canaslan.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountIU {
	
	@NotNull
    private String accountNumber;

	@NotNull
    private String iban;

	@NotNull
    private BigDecimal amount;

	@NotNull
    private CurrencyType currencyType;

}
