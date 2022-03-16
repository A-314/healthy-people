package com.emelyan.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
public  class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private String surname;
    private String patronymic;

    @Column(name="birth_date")
    private LocalDate birthdate;

    @Column(name = "phone")
    private String phone;
    @Column(name = "second_phone")
    private String secondPhone;

    @Column(name ="fact_address")
    private String factAddress;

    private String email;

    @Override
    public String toString(){return surname+" "+name+" "+patronymic;}
}

