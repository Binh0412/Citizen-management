package com.example.CitizenManagement.dto;

import java.math.BigDecimal;

public interface ExpenseViewDtoItf {
	
	Long getId();
	
	String getMon();
	
	String getApartmentName();
	
	BigDecimal getElectricityExpense();
	
	BigDecimal getWaterExpense();
	
	BigDecimal getGarbageExpense();
	
	BigDecimal getTotalExpense();
	
	BigDecimal getManagementExpense();
	
	Boolean getConditions();
}
