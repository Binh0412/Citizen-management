package com.example.CitizenManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CitizenManagement.dto.ApartmentViewDtoItf;
import com.example.CitizenManagement.dto.ApartmentViewDtooItf;
import com.example.CitizenManagement.entity.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
	
	
	
	@Query(value = "SELECT a FROM Apartment a WHERE a.id = :id  ")
	Apartment getApartment(@Param("id") Long id);
	
	@Query(value = "SELECT a FROM Apartment a WHERE a.floor = :floor AND a.roomNo = :room_no ")
	Apartment getByFloorAndRoomNo(@Param("floor") Integer floor,@Param("room_no") Integer roomNo);
	
	@Query(value = " SELECT a.id as id, CONCAT(a.floor,'.',LPAD(a.room_no, 2, '0')) as name FROM Apartment a ORDER BY name ", nativeQuery = true)
	List<ApartmentViewDtoItf> getListApartmentViewDto();
	
	@Query(value = "SELECT a.id, a.floor as floor, a.room_no as roomNo, a.conditions, c.name as apartmentOwnerName, a.apartment_owner_id as apartmentOwnerId, e.id as Expense "
			+ " FROM Apartment a "
			+ " LEFT JOIN Citizen c on a.apartment_owner_id = c.id"
			+ " LEFT JOIN Expense e on a.id = e.apartment_id AND e.mon = :mon "
			+ " ORDER BY a.floor, a.room_no ", nativeQuery = true)
	Page<ApartmentViewDtooItf> getListApartment(Pageable pageable, @Param("mon") String mon);
	
	@Query(value = "SELECT a.id as id, a.conditions as conditions, e.id as expense FROM Apartment a "
			+ "	LEFT JOIN Expense e on a.id = e.apartment_id AND e.mon = :mon WHERE a.id = :id", nativeQuery = true)
	ApartmentViewDtooItf getApartmentViewDtoo(@Param("id") Long id, @Param("mon") String mon);
}
