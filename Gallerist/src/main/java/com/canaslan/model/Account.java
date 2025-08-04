package com.canaslan.model;

import com.canaslan.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{

    @Column
    private String accountNumber;

    @Column
    private String iban;

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING) // ordinal olursa 0 1 diye yansir string secilirse tl usd olarak veri tabanina yansir
    private CurrencyType currencyType;
}
