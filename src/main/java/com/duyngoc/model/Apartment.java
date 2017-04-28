package com.duyngoc.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="apartment")
public class Apartment implements Comparable<Apartment> {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String street;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "apartment",fetch = FetchType.EAGER)
	private List<ImageUrl> imageUrls;
	private String city;
	private String owner;
	private double price;
	private String apartmentId;
	private String longitude;
	private boolean approved;
	private String latitude;
	private boolean available;
	private int bedrooms;
	private float bathrooms;
	private String finishedSqFt;
	private String lotSizeSqFt;
	private String yearBuilt;
	private String yearUpdated;
	private String numFloors;
	private String process;
	private String rooms;
	private String viewPoint;
	private String parkingType;
	private String heatingSourcesGas;
	public Apartment(){
		
	}
	
/*	public Apartment(String apartmentid, Boolean approved, String owner,String process,String bathrooms,String view,String parkingType, String longitude,String latitude, String heatingSourcesGas, String street, String city, double price, String rooms, String bedrooms, String finishedSqft, String lotSizeSqft,String yearBuilt, String yearUpdated, String floors){
		this.street=street;
		this.city=city;
		this.price=price;
		this.rooms=rooms;
		this.bedrooms=bedrooms;
		this.finishedSqFt=finishedSqft;
		this.lotSizeSqFt=lotSizeSqft;
		this.yearBuilt=yearBuilt;
		this.yearUpdated=yearUpdated;
		this.numFloors=floors;
		this.owner=owner;
		this.bathrooms=bathrooms;
		this.process=process;
		this.view=view;
		this.parkingType=parkingType;
		this.heatingSourcesGas=heatingSourcesGas;
		this.longitude=longitude;
		this.latitude=latitude;
		this.apartmentid=apartmentid;
		this.approved=approved;
	}*/
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean avaialble) {
		this.available = avaialble;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}



	@Override
	public int compareTo(Apartment o) {
		// TODO Auto-generated method stub
		return 1;
	}

	public String getViewPoint() {
		return viewPoint;
	}

	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
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
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public float getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(float bathrooms) {
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}

	public List<ImageUrl> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<ImageUrl> imageUrls) {
		this.imageUrls = imageUrls;
	}

	
	
	
	
	
}
