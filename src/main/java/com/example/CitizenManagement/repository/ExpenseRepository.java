package com.example.CitizenManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CitizenManagement.dto.ExpenseViewDtoItf;
import com.example.CitizenManagement.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	@Query(value = "SELECT e FROM Expense e WHERE e.apartmentId = :apartmentId AND e.mon = :mon")
	Expense getExpensebyApartmentIdAndMonth(@Param("apartmentId") Long apartmentId, @Param("mon") String mon);
	
	@Query(value = "SELECT e.id as id, e.mon as mon, e.electricity_expense as electricityExpense, e.water_expense as waterExpense, e.garbage_expense as garbageExpense, e.total_expense as totalExpense, e.management_expense as managementExpense, e.conditions as conditions, CONCAT(a.floor,'.',LPAD(a.room_no, 2, '0')) as apartmentName "
			+ " FROM Expense e "
			+ " INNER JOIN Apartment a on e.apartment_id = a.id ", nativeQuery = true)
	Page<ExpenseViewDtoItf> getListExpense(Pageable pageable);
	
	@Query(value = "SELECT e FROM Expense e WHERE e.id = :id ")
	Expense getExpense(@Param("id") Long id);
}
