package com.emelyan.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    private String phone;

    private String second_phone;
    private String fact_address;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;

    @Convert(converter = GenderConverter.class)
    public Gender gender;

    @Override
    public String toString(){return name+" "+surname+" "+patronymic;}

    @Converter
    static class GenderConverter implements AttributeConverter<Gender, Character> {
        public Character convertToDatabaseColumn(Gender value) {
            if (value == null) {
                return null;
            }
            return value.getCode();
        }

        public Gender convertToEntityAttribute(Character value) {
            if (value == null) {
                return null;
            }
            return Gender.fromCode(value);
        }
    }
}
