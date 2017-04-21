package com.duyngoc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.duyngoc.model.Student;
import com.duyngoc.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/ehome")
public class HelloController {
	@Autowired
	private StudentRepository studentRepo;
	
	private void getEmployees() throws IOException
	{
	    final String uri = "http://www.zillow.com/webservice/GetUpdatedPropertyDetails.htm?zws-id=X1-ZWz1frvxkp0xzf_94p25&zpid=48749425";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String xml = restTemplate.getForObject(uri, String.class);
	     System.out.println("fsadfsd");
	     ObjectMapper xmlMapper = new XmlMapper();
	     JsonNode node = xmlMapper.readTree(xml.getBytes());

	     ObjectMapper jsonMapper = new ObjectMapper();
	     String json = jsonMapper.writeValueAsString(node);
	    System.out.println(json);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> home() {
		try {
			getEmployees();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
