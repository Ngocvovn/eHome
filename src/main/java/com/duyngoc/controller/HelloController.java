package com.duyngoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyngoc.model.Student;
import com.duyngoc.repository.StudentRepository;

@RestController
@RequestMapping("/ehome")
public class HelloController {
	@Autowired
	private StudentRepository studentRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> home() {
		List<Student> students = (List<Student>) studentRepo.findAll();
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String greet() {
		return "hello wolrd";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		studentRepo.save(student);
		return new ResponseEntity<Student>(student,HttpStatus.OK);
	}
}
