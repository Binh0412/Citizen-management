package com.example.CitizenManagement.service;

import org.springframework.data.domain.Pageable;

import com.example.CitizenManagement.entity.Citizen;
import com.example.CitizenManagement.utils.DataResponse;

public interface CitizenService {

	DataResponse createCitizen(Citizen c);

	DataResponse getListCitizen(Pageable pageable);

	DataResponse getCitizen(Long id);

	DataResponse getCitizenByApartmentId(Long apartmentId);

	DataResponse deleteCitizen(Long id);

	DataResponse updateCitizen(Citizen c);

	DataResponse getCitizenByOwnerId(Pageable pageable);

}
