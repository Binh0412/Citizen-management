package com.example.CitizenManagement.service;

import org.springframework.data.domain.Pageable;

import com.example.CitizenManagement.entity.Vehicle;
import com.example.CitizenManagement.utils.DataResponse;

public interface VehicleService {

	DataResponse createVehicle(Vehicle v);

	DataResponse getListVehicle(Pageable pageable);

	DataResponse getVehicle(Long id);

//	DataResponse getVehicleByApartmentId(Long apartmentId);

	DataResponse deleteVehicle(Long id);

	DataResponse updateVehicle(Vehicle v);

	DataResponse getVehicleByOwnerId(Long vehicleOwnerId);

	DataResponse getListVehicle1();

}
