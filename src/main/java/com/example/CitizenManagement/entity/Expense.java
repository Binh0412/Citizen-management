package com.example.CitizenManagement.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mon", columnDefinition = "VARCHAR(50)", nullable = false)
	private String mon;
	
	@Column(name = "apartment_id", columnDefinition = "BIGINT", nullable = false)
	private Long apartmentId;
	
	@Column(name = "electricity_expense", columnDefinition = "DECIMAL(18,0)", nullable = false)
	private BigDecimal electricityExpense;
	
	@Column(name = "water_expense", columnDefinition = "DECIMAL(18,0)", nullable = false)
	private BigDecimal waterExpense;
	
	@Column(name = "garbage_expense", columnDefinition = "DECIMAL(18,0)", nullable = false)
	private BigDecimal garbageExpense;
	
	@Column(name = "total_expense", columnDefinition = "DECIMAL(18,0)", nullable = false)
	private BigDecimal totalExpense;
	
	@Column(name = "management_expense", columnDefinition = "DECIMAL(18,0)", nullable = false)
	private BigDecimal managementExpense;
	
	@Column(name = "conditions", columnDefinition = "BIT", nullable = false)
	private Boolean conditions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Long apartmentId) {
		this.apartmentId = apartmentId;
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
	

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
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
