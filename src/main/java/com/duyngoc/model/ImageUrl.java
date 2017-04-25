package com.duyngoc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "imageUrl")
public class ImageUrl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String url;
	private String aparmentid;
	
	public String getAparmentid() {
		return aparmentid;
	}

	public void setAparmentid(String aparmentid) {
		this.aparmentid = aparmentid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apartmentId")
	private Apartment apartment;

	public ImageUrl() {
	}

	public ImageUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}


}

