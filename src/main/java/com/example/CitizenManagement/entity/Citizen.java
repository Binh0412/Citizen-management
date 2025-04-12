package com.example.CitizenManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "citizen")
public class Citizen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;
	
	@Column(name = "gender", columnDefinition = "BIT", nullable = false)
	private Boolean gender;
	
	@Column(name = "date_of_birth", columnDefinition = "VARCHAR(10)", nullable = false)
	private String dateOfBirth;
	
	@Column(name = "identification_number", columnDefinition = "VARCHAR(12)")
	private String identificationNumber;
	
	@Column(name = "phone", columnDefinition = "VARCHAR(10)")
	private String phone;
	
	@Column(name = "apartment_id", columnDefinition = "BIGINT", nullable = false)
	private Long apartmentId;
	
	@Column(name = "day_in", columnDefinition = "VARCHAR(10)", nullable = false)
	private String dayIn;
	
	@Column(name = "is_owner", columnDefinition = "BIT", nullable = false)
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
		this.dateOfBirth = dateOfBirth;
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


	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
	}

	public String getDayIn() {
		return dayIn;
	}

	public void setDayIn(String dayIn) {
		this.dayIn = dayIn;
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
 // extend chỉ kế thừa 1 con kế thwuaf 1 cha/
	// dùng từ khóa implement
	
	
}
