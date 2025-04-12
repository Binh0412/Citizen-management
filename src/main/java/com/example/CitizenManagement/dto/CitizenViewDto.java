package com.example.CitizenManagement.dto;

import com.example.CitizenManagement.utils.Common;
import com.example.CitizenManagement.utils.Constant;


public class CitizenViewDto {
	
	private Long id;
	
	private String name;
	
	private Boolean gender;
	
	private String dateOfBirth;

	private String identificationNumber;
	
	private String phone;
	
	private String apartmentName;
	
	private String dayIn;
	
	private Boolean isOwner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = Common.convertStringDate(dateOfBirth, Constant.YYYYMMDD, Constant.DD__MM__YYYY);
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getDayIn() {
		return dayIn;
	}

	public void setDayIn(String dayIn) {
		this.dayIn = Common.convertStringDate(dayIn, Constant.YYYYMMDD, Constant.DD__MM__YYYY);
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	
}
