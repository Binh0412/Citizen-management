package com.example.CitizenManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name = "username", columnDefinition = "VARCHAR(30)")
	private String username;
	
	@Column(name = "fullname", columnDefinition = "VARCHAR(255)", nullable = false)
	private String fullname;
	
	@Column(name = "password", columnDefinition = "VARCHAR(200)", nullable = false)
	private String password;
	
	@Column(name = "authority", columnDefinition = "VARCHAR(500)", nullable = false)
	private String authority;
	
	@Column(name = "apartment_owner_id", columnDefinition = "BIGINT", nullable = true)
	private Long apartmentOwnerId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Long getApartmentOwnerId() {
		return apartmentOwnerId;
	}

	public void setApartmentOwnerId(Long apartmentOwnerId) {
		this.apartmentOwnerId = apartmentOwnerId;
	}
	
	
}
