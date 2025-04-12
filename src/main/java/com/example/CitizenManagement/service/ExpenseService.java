package com.example.CitizenManagement.service;

import org.springframework.data.domain.Pageable;

import com.example.CitizenManagement.entity.Expense;
import com.example.CitizenManagement.utils.DataResponse;

public interface ExpenseService {

	DataResponse createExpense(Expense ex);

	DataResponse getListExpense(Pageable pageable);

	DataResponse updateExpense(Expense e);

	DataResponse getExpense(Long id);

}
