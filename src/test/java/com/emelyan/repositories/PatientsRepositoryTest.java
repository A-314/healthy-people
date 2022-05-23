package com.emelyan.repositories;

import com.emelyan.models.Patient;
import com.emelyan.models.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@PropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
@Tag("patient")
public class PatientsRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;

    private Patient patient;

    @Before
    public void init(){
        Person person = Person.builder().name("Jack").surname("Smith").patronymic("Leo").build();
        patient = Patient.builder().person(person).build();
    }

    @Test
    public void get0IfEmptyList(){
        assertThat(patientRepository.findPatientsWithFilter("").size()).isEqualTo(0);
    }

    @Test
    public void findDoctorWithFilter() {

        patientRepository.save(patient);

        assertAll(
            ()->assertThat(patientRepository.findPatientsWithFilter("Jack").get(0).getId()).isEqualTo(patient.getId()),
            ()->assertThat(patientRepository.findPatientsWithFilter("Smith").get(0).getId()).isEqualTo(patient.getId()),
            ()->assertThat(patientRepository.findPatientsWithFilter("Leo").get(0).getId()).isEqualTo(patient.getId())
        );

    }
    @Test
    public void findDoctorsWithFilterFail(){
        patientRepository.save(patient);

        List<Patient> lists = patientRepository.findPatientsWithFilter("Tom");

        assertThat(lists.size()).isZero();
    }
    @Test
    public void findDoctorsWithFilterLowerCase(){
        patientRepository.save(patient);

        assertAll(
            ()->assertThat(patientRepository.findPatientsWithFilter("smith")).size().isNotZero(),
            ()->assertThat(patientRepository.findPatientsWithFilter("jack")).size().isNotZero(),
            ()->assertThat(patientRepository.findPatientsWithFilter("leo")).size().isNotZero()
        );
    }
    @Test
    public void findDoctorsWithFilterMiddlePartWord(){
        patientRepository.save(patient);

        assertAll(
                ()->assertThat(patientRepository.findPatientsWithFilter("mit")).size().isNotZero(),
                ()->assertThat(patientRepository.findPatientsWithFilter("ac")).size().isNotZero(),
                ()->assertThat(patientRepository.findPatientsWithFilter("e")).size().isNotZero()
        );
    }

}