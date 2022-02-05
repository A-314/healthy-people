package com.emelyan.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    private String name;
    private String surname;
    private String patronymic;

    @Override
    public String toString(){return name+" "+surname+" "+patronymic;}

}
