package com.example.CitizenManagement.service;

import org.springframework.data.domain.Pageable;

import com.example.CitizenManagement.entity.Apartment;
import com.example.CitizenManagement.utils.DataResponse;

public interface ApartmentService {

	DataResponse createApartment(Apartment a);

	DataResponse getApartment(Long id);

	DataResponse updateApartment(Apartment a);

	DataResponse getListApartmentName();

	DataResponse getListApartment(Pageable pageable);

}
