package com.example.CitizenManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CitizenManagement.dto.VehicleViewItfDto;
import com.example.CitizenManagement.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	@Query(value = "SELECT v.id, v.license_plate as licensePlate, v.vehicle_type as vehicleType, v.park_location as parkLocation, c.name as name, CONCAT(a.floor,'.',LPAD(a.room_no, 2, '0')) as apartmentName "
			+ " FROM Vehicle v "
			+ " INNER JOIN Citizen c on v.vehicle_owner_id = c.id "
			+ " INNER JOIN Apartment a on c.apartment_id = a.id ", nativeQuery = true)
	Page<VehicleViewItfDto> getListVehicle(Pageable pageable);
	
	@Query(value = "SELECT v FROM Vehicle v WHERE v.id = :id ")
	Vehicle getVehicle(@Param("id") Long id);
	
	@Query(value = "SELECT v FROM Vehicle v")
	List<Vehicle> getListVehicle();
	
	@Query(value = "SELECT v FROM Vehicle v WHERE v.vehicleOwnerId = :vehicleOwnerId ")
	List<Vehicle> getVehicleByOwnerId(@Param("vehicleOwnerId") Long vehicleOwnerId);
	
//	@Query(value = "SELECT v FROM Vehicle v WHERE v.apartmentId = :apartmentId ")
//	List<Vehicle> getVehicleByApartmentId(@Param("apartmentId") Long apartmentId);
	
	@Modifying
	@Query(value = "DELETE FROM Vehicle WHERE id = :id", nativeQuery = true)
	void deleteVehicle(@Param("id") Long id);
	
	@Query(value = "SELECT v FROM Vehicle v WHERE v.parkLocation = :parkLocation ")
	Vehicle getByParkLocation(@Param("parkLocation") String parkLocation);

	

	

}
