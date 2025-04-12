package com.example.CitizenManagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApartmentPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "floor", columnDefinition = "INT", nullable = false)
	private Integer floor;
	
	@Column(name = "room_no", columnDefinition = "INT", nullable = false)
	private Integer roomNo;

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
	
	
}
