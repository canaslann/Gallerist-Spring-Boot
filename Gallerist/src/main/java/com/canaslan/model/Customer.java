package com.canaslan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String tckn;

    @Column
    private Date dateOfBirth;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;
}
