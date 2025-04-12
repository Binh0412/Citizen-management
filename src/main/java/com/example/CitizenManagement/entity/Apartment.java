package com.example.CitizenManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apartment")
public class Apartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	
	@Column(name = "floor", columnDefinition = "INT", nullable = false)
	private Integer floor;
	
	@Column(name = "room_no", columnDefinition = "INT", nullable = false)
	private Integer roomNo; 
	
//	@EmbeddedId
//	private ApartmentPK id;
	
	@Column(name = "apartment_owner_id", columnDefinition = "BIGINT", nullable = true)
	private Long apartmentOwnerId;
	
	@Column(name = "conditions", columnDefinition = "INT", nullable = false)
	private Integer conditions; // 0: chưa có chủ 1: có người thuê 2: có người mua

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Long getApartmentOwnerId() {
		return apartmentOwnerId;
	}

	public void setApartmentOwnerId(Long apartmentOwnerId) {
		this.apartmentOwnerId = apartmentOwnerId;
	}

	public Integer getConditions() {
		return conditions;
	}

	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}

	
	
}
