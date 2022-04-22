package com.emelyan.repositories;

import com.emelyan.models.Doctor;
import com.emelyan.models.Person;
import org.junit.Before;
import org.junit.Test;
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
public class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    private Doctor doctor;

    @Before
    public void init(){
        Person person = Person.builder().name("Jack").surname("Smith").patronymic("Leo").build();
        doctor = Doctor.builder().person(person).build();
    }
    @Test
    public void findDoctorsWithFilter() {

        doctorRepository.save(doctor);

        assertAll(
            ()->assertThat(doctorRepository.findDoctorsWithFilter("Jack").get(0).getId()).isEqualTo(doctor.getId()),
            ()->assertThat(doctorRepository.findDoctorsWithFilter("Smith").get(0).getId()).isEqualTo(doctor.getId()),
            ()->assertThat(doctorRepository.findDoctorsWithFilter("Leo").get(0).getId()).isEqualTo(doctor.getId())
        );

    }
    @Test
    public void findDoctorsWithFilterFail(){
        doctorRepository.save(doctor);

        List<Doctor> lists = doctorRepository.findDoctorsWithFilter("Tom");

        assertThat(lists.size()).isZero();
    }
    @Test
    public void findDoctorsWithFilterLowerCase(){
        doctorRepository.save(doctor);

        assertAll(
            ()->assertThat(doctorRepository.findDoctorsWithFilter("smith")).size().isNotZero(),
            ()->assertThat(doctorRepository.findDoctorsWithFilter("jack")).size().isNotZero(),
            ()->assertThat(doctorRepository.findDoctorsWithFilter("leo")).size().isNotZero()
        );
    }
    @Test
    public void findDoctorsWithFilterMiddlePartWord(){
        doctorRepository.save(doctor);

        assertAll(
                ()->assertThat(doctorRepository.findDoctorsWithFilter("mit")).size().isNotZero(),
                ()->assertThat(doctorRepository.findDoctorsWithFilter("ac")).size().isNotZero(),
                ()->assertThat(doctorRepository.findDoctorsWithFilter("e")).size().isNotZero()
        );
    }

}