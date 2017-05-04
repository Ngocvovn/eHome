package com.duyngoc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.duyngoc.model.Apartment;
import com.duyngoc.model.ImageUrl;
import com.duyngoc.repository.ApartmentRepository;
import com.duyngoc.repository.ImageUrlRepostiory;

@RestController
@RequestMapping("/ehome")
public class DataFromPublicAPI {

	private static final String ID = "X1-ZWz1frvxkp0xzf_94p25";

	@Autowired
	private ApartmentRepository apartRepo;

	@Autowired
	private ImageUrlRepostiory imageRepo;

	public String findZpidFromXMLresponse(String xml) {
		String[] split = xml.split(">");
		String zpid = "";
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("<zpid")) {
				zpid = split[i + 1].split("<")[0];
				break;

			}
		}

	
		return zpid;
	}

	@RequestMapping(value = "/address/{city}/{street}", method = RequestMethod.GET)
	public String getApartmentFromPublicAPI(@PathVariable String city, @PathVariable String street) throws IOException {
		String uri = "http://www.zillow.com/webservice/GetSearchResults.htm?zws-id="+ID+"&address=" + street
				+ "&citystatezip=" + city;

		RestTemplate restTemplate = new RestTemplate();
		String xml = restTemplate.getForObject(uri, String.class);
		Apartment apartment = new Apartment();
		apartment.setApartmentId(findZpidFromXMLresponse(xml));
		apartment.setCity(city);
		apartment.setStreet(street);
		apartment.setOwner("eHome");
		getDetailsOfApartment(apartment);
		return apartment.getApartmentId()+apartment.getRooms();
	}

	@RequestMapping(value = "/apartment", method = RequestMethod.GET)
	public Apartment getDetailsOfApartment(Apartment apartment) throws IOException {
		String uri = "http://www.zillow.com/webservice/GetUpdatedPropertyDetails.htm?zws-id=X1-ZWz1frvxkp0xzf_94p25&zpid="
				+ apartment.getApartmentId();
		
		RestTemplate restTemplate = new RestTemplate();
		String xml = restTemplate.getForObject(uri, String.class);
		System.out.println(apartment.getApartmentId()+xml);
		if (extractDetailOfApartment(apartment, xml)) {
			apartment.setAvailable(true);
			apartRepo.save(apartment);
		}
		return apartment;
	}

	public boolean extractDetailOfApartment(Apartment apartment, String xml) {

		String[] split = xml.split(">");
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("Error")) {
				System.out.println("no accessiable");
				return false;
			} else if (split[i].equals("<url")) {
			
				ImageUrl url = new ImageUrl();
				url.setApartmentId(apartment.getApartmentId());
				url.setUrl(split[i + 1].split("<")[0]);
				imageRepo.save(url);
			}
			else if (split[i].equals("<longitude")) {
			
				apartment.setLongitude(split[i + 1].split("<")[0]);
			} 
			else if (split[i].equals("<latitude")) {
				
				apartment.setLatitude(split[i + 1].split("<")[0]);
			} 
			else if (split[i].equals("<rooms")) {
				
				apartment.setRooms(split[i + 1].split("<")[0]);
			} 
			else if (split[i].equals("<parkingType")) {
				
				apartment.setParkingType(split[i + 1].split("<")[0]);
			} 
			else if (split[i].equals("<bedrooms")) {
				
				apartment.setBedrooms(Integer.parseInt(split[i + 1].split("<")[0]));
			} 
			else if (split[i].equals("<bathrooms")) {
				
				apartment.setBathrooms(Float.parseFloat(split[i + 1].split("<")[0]));
			} 
			else if (split[i].equals("<finishedSqFt")) {
				
				apartment.setFinishedSqFt(Float.parseFloat(split[i + 1].split("<")[0]));
			}
			else if (split[i].equals("<lotSizeSqFt")) {
	
				apartment.setLotSizeSqFt(Float.parseFloat(split[i + 1].split("<")[0]));
			}
			else if (split[i].equals("<yearBuilt")) {
			
				apartment.setYearBuilt(split[i + 1].split("<")[0]);
			} 
			else if (split[i].equals("<yearUpdated")) {
				
				apartment.setYearUpdated(split[i + 1].split("<")[0]);
			} 
			else if (split[i].equals("<numFloors")) {
				
				apartment.setNumFloors(split[i + 1].split("<")[0]);
			}
			else if (split[i].equals("<heatingSources")) {
			
				apartment.setHeatingSourcesGas(split[i + 1].split("<")[0]);
			}
			else if (split[i].equals("<view")) {
				
				apartment.setViewPoint(split[i + 1].split("<")[0]);
			}
		}
		return true;

	}
}
