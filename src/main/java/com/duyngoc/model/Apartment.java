package com.duyngoc.model;

import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="apartment")
public class Apartment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String street;
	private String city;
	private double price;
	
	
	private String rooms;
	
	
	private String bedrooms;
	private String bathrooms;
	private String finishedSqFt;
	private String lotSizeSqFt;
	private String yearBuilt;
	private String yearUpdated;
	private String numFloors;
	
	
	
	public Apartment(){
		
	}
	
	public Apartment(String street, String city, double price, String rooms, String bedrooms, String finishedSqft, String lotSizeSqft,String yearBuilt, String yearUpdated, String floors){
		this.street=street;
		this.city=city;
		this.price=price;
		this.rooms=rooms;
		this.bedrooms=bedrooms;
		this.finishedSqFt=finishedSqft;
		this.lotSizeSqFt=lotSizeSqft;
		this.yearBuilt=yearBuilt;
		this.yearUpdated=yearUpdated;
		this.numFloors=numFloors;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public String getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}
	public String getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(String bathrooms) {
		this.bathrooms = bathrooms;
	}
	public String getFinishedSqFt() {
		return finishedSqFt;
	}
	public void setFinishedSqFt(String finishedSqFt) {
		this.finishedSqFt = finishedSqFt;
	}
	public String getLotSizeSqFt() {
		return lotSizeSqFt;
	}
	public void setLotSizeSqFt(String lotSizeSqFt) {
		this.lotSizeSqFt = lotSizeSqFt;
	}
	public String getYearBuilt() {
		return yearBuilt;
	}
	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	public String getYearUpdated() {
		return yearUpdated;
	}
	public void setYearUpdated(String yearUpdated) {
		this.yearUpdated = yearUpdated;
	}
	public String getNumFloors() {
		return numFloors;
	}
	public void setNumFloors(String numFloors) {
		this.numFloors = numFloors;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getParkingType() {
		return parkingType;
	}
	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}
	public String getHeatingSourcesGas() {
		return heatingSourcesGas;
	}
	public void setHeatingSourcesGas(String heatingSourcesGas) {
		this.heatingSourcesGas = heatingSourcesGas;
	}
	private String view;
	private String parkingType;
	private String heatingSourcesGas;

	
	
	
	
	
}