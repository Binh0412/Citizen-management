package com.example.CitizenManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CitizenManagement.entity.Account;
import com.example.CitizenManagement.entity.Citizen;
import com.example.CitizenManagement.service.AccountService;
import com.example.CitizenManagement.service.CitizenService;
import com.example.CitizenManagement.utils.DataResponse;

@RestController
@RequestMapping("/api/citizen")
public class CitizenController {
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public DataResponse createCitizen(@RequestBody Citizen c) {
		DataResponse res = citizenService.createCitizen(c);
		return res; 
	}
	
	@GetMapping("/get-all")
	public DataResponse getListCitizen2(Pageable pageable) {
		Account a = accountService.getAccountLogin();
		if(a.getAuthority() == "ROLE_ADMIN") {
			DataResponse res = citizenService.getListCitizen(pageable);	
			return res;
		}else {
			DataResponse res = citizenService.getCitizenByOwnerId(a.getApartmentOwnerId(), pageable);
			return res;
		}
	}
	
	@GetMapping("/get-all")
	public DataResponse getListCitizen(Pageable pageable) {
		DataResponse res = citizenService.getListCitizen(pageable);
		return res;
	}
	
	@GetMapping("/get-citizen/{id}")
	public DataResponse getCitizen(@PathVariable("id") Long id) {
		DataResponse res = citizenService.getCitizen(id);
		return res; 
	}
	
	@GetMapping("/get-citizen-by-apertment-id/{apartmentId}")
	public DataResponse getCitizenByApartmentId(@PathVariable("apartmentId") Long apartmentId) {
		DataResponse res = citizenService.getCitizenByApartmentId(apartmentId);
		return res; 
	}
	
	@DeleteMapping("/delete/{id}")
	public DataResponse deleteCitizen(@PathVariable("id") Long id) {
		DataResponse res = citizenService.deleteCitizen(id);
		return res; 
	}
	
	@PutMapping("/update")
	public DataResponse updateCitizen(@RequestBody Citizen c) {
		DataResponse res = citizenService.updateCitizen(c);
		return res;
	}
}

/*
 * byte
 * short
 * int
 * long
 * float
 * double
 * char
 * boolean
 * */
