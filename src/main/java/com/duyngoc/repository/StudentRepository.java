package com.duyngoc.repository;

import org.springframework.data.repository.CrudRepository;

import com.duyngoc.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
