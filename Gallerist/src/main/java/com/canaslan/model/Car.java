package com.canaslan.model;

import com.canaslan.enums.CarStatusType;
import com.canaslan.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{

    @Column
    private String plaka;

    @Column
    private String model;

    @Column
    private String brand;

    @Column
    private Integer productionYear;

    @Column
    private BigDecimal price;

    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column
    private BigDecimal damagePrice;

    @Column
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;
}
