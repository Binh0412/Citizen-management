package com.example.CitizenManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CitizenManagement.entity.Expense;
import com.example.CitizenManagement.service.ExpenseService;
import com.example.CitizenManagement.utils.DataResponse;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping("/create")
	public DataResponse createExpense(@RequestBody Expense ex) {
		DataResponse res = expenseService.createExpense(ex);
		return res;
	}
	
	@GetMapping("/get-all")
	public DataResponse getListExpense(Pageable pageable) {
		DataResponse res = expenseService.getListExpense(pageable);
		return res;
	}
	
	@PutMapping("/update")
	public DataResponse updateExpense(@RequestBody Expense e) {
		DataResponse res = expenseService.updateExpense(e);
		return res;
	}
	
	@GetMapping("/get-expense/{id}")
	public DataResponse getExpense(@PathVariable("id") Long id) {
		DataResponse res = expenseService.getExpense(id);
		
		return res; 
	}
	
}
/*
 * byte
 * short
 * int --> Integer
 * long --> Long
 * double
 * float
 * boolean
 * char
 * 
 * */
