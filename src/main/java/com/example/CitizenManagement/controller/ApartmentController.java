package com.example.CitizenManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CitizenManagement.entity.Apartment;
import com.example.CitizenManagement.service.ApartmentService;
import com.example.CitizenManagement.utils.DataResponse;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
	
	@Autowired
	private ApartmentService apartmentService;
	
	@PostMapping("/create")
	public DataResponse createApartment(@RequestBody Apartment a) {
		DataResponse res = apartmentService.createApartment(a);
		return res; 
	}
	
	@GetMapping("/get-all")
	public DataResponse getListApartment(Pageable pageable) {
		DataResponse res = apartmentService.getListApartment(pageable);
		return res;
	}
	
	@GetMapping("/get-all-apartment-name")
	public DataResponse getListApartmentName() {
		DataResponse res = apartmentService.getListApartmentName();
		return res;
	}
	
	@GetMapping("/get-apartment/{id}")
	public DataResponse getApartment(@PathVariable("id") Long id) {
		DataResponse res = apartmentService.getApartment(id);
		return res; 
	}
	
	
	@PostMapping("/update")
	public DataResponse updateApartment(@RequestBody Apartment a) {
		DataResponse res = apartmentService.updateApartment(a);
		return res;
	}
}
