package com.example.CitizenManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CitizenManagement.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query(value = "SELECT a FROM Account a WHERE a.username = :username ")
	Account getAccountByUsername(@Param("username") String username);

}
