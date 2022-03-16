package com.emelyan.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emelyan.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
