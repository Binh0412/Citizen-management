package com.example.CitizenManagement.dto;

public class ApartmentViewDtoo {
	
	private Long id;
	
	private Integer floor;
	
	private Integer roomNo;
	
	private Long apartmentOwnerId;
	
	private String apartmentOwnerName;
	
	private Integer conditions;
	
	private Long expense; 

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

	public String getApartmentOwnerName() {
		return apartmentOwnerName;
	}

	public void setApartmentOwnerName(String apartmentOwnerName) {
		this.apartmentOwnerName = apartmentOwnerName;
	}

	public Integer getConditions() {
		return conditions;
	}

	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}

	public Long getExpense() {
		return expense;
	}

	public void setExpense(Long expense) {
		this.expense = expense;
	}
	
	
}
