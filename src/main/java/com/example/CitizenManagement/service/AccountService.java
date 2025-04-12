package com.example.CitizenManagement.service;

import com.example.CitizenManagement.entity.Account;
import com.example.CitizenManagement.utils.DataResponse;

public interface AccountService {

	public Account getAccountByUsername(String username);

	public DataResponse register(Account accountRegister);

	public Account getAccountLogin();

}
