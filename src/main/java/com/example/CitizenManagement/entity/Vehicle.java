package com.example.CitizenManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "vehicle_owner_id", columnDefinition = "BIGINT", nullable = false)
	private Long vehicleOwnerId;
	
	@Column(name = "license_plate", columnDefinition = "VARCHAR(50)", nullable = false)
	private String licensePlate;
	
	@Column(name = "vehicle_type", columnDefinition = "INT", nullable = false)
	private Integer vehicleType; // 0 1 2
	
	@Column(name = "park_location", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
	private String parkLocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVehicleOwnerId() {
		return vehicleOwnerId;
	}

	public void setVehicleOwnerId(Long vehicleOwnerId) {
		this.vehicleOwnerId = vehicleOwnerId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getParkLocation() {
		return parkLocation;
	}

	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}

	
	
}
