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

import com.example.CitizenManagement.entity.Vehicle;
import com.example.CitizenManagement.service.VehicleService;
import com.example.CitizenManagement.utils.DataResponse;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/create")
	public DataResponse createVehicle(@RequestBody Vehicle v) {
		DataResponse res = vehicleService.createVehicle(v);
		return res; 
	}
	
	@GetMapping("/get-all")
	public DataResponse getListVehicle(Pageable pageable) {
		DataResponse res = vehicleService.getListVehicle(pageable);
		return res;
	}
	
	@GetMapping("/get-all1")
	public DataResponse getListVehicle1() {
		DataResponse res = vehicleService.getListVehicle1();
		return res;
	}
	
	@GetMapping("/get-vehicle/{id}")
	public DataResponse getVehicle(@PathVariable("id") Long id) {
		DataResponse res = vehicleService.getVehicle(id);
		return res; 
	}
	
	@GetMapping("/get-vehicle-by-owner-id/{vehicleOwnerId}")
	public DataResponse getVehicleByOwnerId(@PathVariable("vehicleOwnerId") Long vehicleOwnerId) {
		DataResponse res = vehicleService.getVehicleByOwnerId(vehicleOwnerId);
		return res; 
	}
	
//	@GetMapping("/get-vehicle-by-apertment-id/{apartmentId}")
//	public DataResponse getVehicleByApartmentId(@PathVariable("apartmentId") Long apartmentId) {
//		DataResponse res = vehicleService.getVehicleByApartmentId(apartmentId);
//		return res; 
//	}
	
	@DeleteMapping("/delete/{id}")
	public DataResponse deleteVehicle(@PathVariable("id") Long id) {
		DataResponse res = vehicleService.deleteVehicle(id);
		return res; 
	}
	
	@PutMapping("/update")
	public DataResponse updateVehicle(@RequestBody Vehicle v) {
		DataResponse res = vehicleService.updateVehicle(v);
		return res;
	}
}
