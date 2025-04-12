package com.example.CitizenManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CitizenManagement.dto.CitizenViewDtoItf;
import com.example.CitizenManagement.entity.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	
	
	
	@Query(value = "SELECT c FROM Citizen c WHERE c.id = :id ")
	Citizen getCitizen(@Param("id") Long id);
	
	@Query(value = "SELECT c FROM Citizen c WHERE c.apartmentId = :apartmentId ")
	List<Citizen> getCitizenByApartmentId(@Param("apartmentId") Long apartmentId);
	
	@Modifying
	@Query(value = "DELETE FROM Citizen WHERE id = :id", nativeQuery = true)
	void deleteCitizen(@Param("id") Long id);
	
	@Query(value = "SELECT c.id as id, c.name as name, c.gender as gender, c.date_of_birth as dateOfBirth, c.identification_number as identificationNumber, "
			+ " c.phone as phone, c.day_in as dayIn, c.is_owner as isOwner, CONCAT(a.floor,'.',LPAD(a.room_no, 2, '0')) as apartmentName "
			+ " FROM Citizen c "
			+ " LEFT JOIN Apartment a on c.apartment_id = a.id ORDER BY apartmentName ", nativeQuery = true)
	Page<CitizenViewDtoItf> getListCitizen(Pageable pageable);
	
}
