package com.example.CitizenManagement.dto;

import java.math.BigDecimal;

public class ExpenseViewDto {
	
	private Long id;
	
	private String mon;
	
	private String apartmentName;
	
	private BigDecimal electricityExpense;
	
	private BigDecimal waterExpense;
	
	private BigDecimal garbageExpense;
	
	private BigDecimal totalExpense;
	
	private BigDecimal managementExpense;
	
	private Boolean conditions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}	

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public BigDecimal getElectricityExpense() {
		return electricityExpense;
	}

	public void setElectricityExpense(BigDecimal electricityExpense) {
		this.electricityExpense = electricityExpense;
	}

	public BigDecimal getWaterExpense() {
		return waterExpense;
	}

	public void setWaterExpense(BigDecimal waterExpense) {
		this.waterExpense = waterExpense;
	}

	public BigDecimal getGarbageExpense() {
		return garbageExpense;
	}

	public void setGarbageExpense(BigDecimal garbageExpense) {
		this.garbageExpense = garbageExpense;
	}

	public BigDecimal getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}

	public BigDecimal getManagementExpense() {
		return managementExpense;
	}

	public void setManagementExpense(BigDecimal managementExpense) {
		this.managementExpense = managementExpense;
	}

	public Boolean getConditions() {
		return conditions;
	}

	public void setConditions(Boolean conditions) {
		this.conditions = conditions;
	}
	
	
}
