package com.example.CitizenManagement.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CitizenManagement.entity.Account;
import com.example.CitizenManagement.repository.AccountRepository;
import com.example.CitizenManagement.security.Authorities;
import com.example.CitizenManagement.service.AccountService;
import com.example.CitizenManagement.utils.Common;
import com.example.CitizenManagement.utils.Constant;
import com.example.CitizenManagement.utils.DataResponse;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Account getAccountByUsername(String username) {
		Account a = accountRepository.getAccountByUsername(username);
		return a;
	}

	@Override
	public DataResponse register(Account accountRegister) {
		DataResponse res = new DataResponse();
		try {
			accountRegister.setAuthority(Authorities.USER);
			accountRegister.setPassword(passwordEncoder.encode(accountRegister.getPassword()));
			accountRepository.save(accountRegister);
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Thành công");
			return res;
		} catch (Exception e) {
			res.setStatus(Constant.ERROR);
			res.setMessage("Lỗi hệ thống");
			return res;
		}
	}
	
	@Override
	public Account getAccountLogin() {
		return this.getAccountByUsername(Common.getCurrentUserLogin().get());
	}

}
