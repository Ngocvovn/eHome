package com.duyngoc.controller;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.net.AprEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.duyngoc.model.Apartment;
import com.duyngoc.model.ImageUrl;
import com.duyngoc.model.Student;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;
import com.duyngoc.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/ehome")
public class RestTemplateController {


	private static final String ID = "zws-id=X1-ZWz1frvxkp0xzf_94p25";
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ApartmentRepository apartRepo;
	
	@Autowired
	private ImageUrlRepostiory imageRepo;
	public String convertToJson(String xml) throws JsonProcessingException, IOException{
		ObjectMapper xmlMapper = new XmlMapper();
		JsonNode node = xmlMapper.readTree(xml.getBytes());

		ObjectMapper jsonMapper = new ObjectMapper();
		String json = jsonMapper.writeValueAsString(node);
		return json;
	}

	@RequestMapping(value = "/address/{city}/{street}", method = RequestMethod.GET)
	public String getZpid(@PathVariable String city,@PathVariable String street) throws JsonProcessingException, IOException {
		String uri = "http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz1frvxkp0xzf_94p25&address="+street+"&citystatezip="+city;
		Apartment apartment = new Apartment();
		RestTemplate restTemplate = new RestTemplate();
		String xml = restTemplate.getForObject(uri, String.class);
		String json = convertToJson(xml);

		String[] split = json.split(",");
		System.out.println(json);
		for (String s : split) {
			System.out.println(s);
			if (s.contains("zpid")) {
				String[] strings = s.split("\"");
				json = strings[strings.length - 1];
				break;

			}
		}
		System.out.println(json);
		apartment.setApartmentid(json);
		apartment.setCity(city);
		apartment.setStreet(street);
		getApartment(apartment,json);
		return json;
	}

	@RequestMapping(value = "/apartment", method = RequestMethod.GET)
	public Apartment getApartment(Apartment apartment,String zpid) throws IOException {
		String uri = "http://www.zillow.com/webservice/GetUpdatedPropertyDetails.htm?zws-id=X1-ZWz1frvxkp0xzf_94p25&zpid="+zpid;
		boolean save = true;
		RestTemplate restTemplate = new RestTemplate();
		String xml = restTemplate.getForObject(uri, String.class);
		System.out.println("fsadfsd"+xml);
		
	
		String[] split = xml.split(">");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
		}

		for (int i = 0; i < split.length; i++) {
			if(split[i].contains("Error")){
				save= false;
				break;
			}
			else if (split[i].equals("<url")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				ImageUrl url = new ImageUrl();
				url.setAparmentid(zpid);
				url.setUrl(split[i+1].split("<")[0]);
				imageRepo.save(url);
			}

			else if (split[i].equals("<street")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
			
			} else if (split[i].contains("zipcode")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				
			}

			else if (split[i].contains("city")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
			}

			else if (split[i].equals("<longitude")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setLongitude(split[i+1].split("<")[0]);
			}
			else if (split[i].equals("<latitude")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setLatitude(split[i+1].split("<")[0]);
			} 
			else if (split[i].equals("<rooms")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setRooms(split[i+1].split("<")[0]);
			} 
			else if (split[i].equals("<parkingType")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setParkingType(split[i+1].split("<")[0]);
			} 
			else if (split[i].equals("<bedrooms")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setBedrooms(split[i+1].split("<")[0]);
			}
			else if (split[i].equals("<bathrooms")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setBathrooms(split[i+1].split("<")[0]);
			} 
			else if (split[i].equals("<finishedSqFt")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setFinishedSqFt(split[i+1].split("<")[0]);
			}
			
			else if (split[i].equals("<lotSizeSqFt")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setLotSizeSqFt(split[i+1].split("<")[0]);
				
			}
			
			else if (split[i].equals("<yearBuilt")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setYearBuilt(split[i+1].split("<")[0]);
			}
			else if (split[i].equals("<yearUpdated")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setYearUpdated(split[i+1].split("<")[0]);
			} 
			else if (split[i].equals("<numFloors")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setNumFloors(split[i+1].split("<")[0]);
			}
			
			else if (split[i].equals("<heatingSources")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setHeatingSourcesGas(split[i+1].split("<")[0]);
			}
			
			else if (split[i].equals("<view")) {
				System.out.println(split[i] + split[i + 1] + split[i + 2]);
				apartment.setView(split[i+1].split("<")[0]);
			}
		}
		if(save){
			apartRepo.save(apartment);
		}
	
		return apartment;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> home() {
	
		List<Student> students = (List<Student>) studentRepo.findAll();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String greet() {
		return "hello wolrd";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		studentRepo.save(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}
