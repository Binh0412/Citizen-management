package com.example.CitizenManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CitizenManagement.dto.AccountLogin;
import com.example.CitizenManagement.dto.LoginResponse;
import com.example.CitizenManagement.entity.Account;
import com.example.CitizenManagement.security.CustomUserDetails;
import com.example.CitizenManagement.security.JwtTokenProvider;
import com.example.CitizenManagement.service.AccountService;
import com.example.CitizenManagement.utils.Constant;
import com.example.CitizenManagement.utils.DataResponse;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	private final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	
	@PostMapping("/register")
	public DataResponse register(@RequestBody Account accountRegister) throws Exception {
		log.debug("Request register");
		DataResponse res = accountService.register(accountRegister);
		return res;
	}
	
	@PostMapping("/login")
	public DataResponse login(@RequestBody AccountLogin accountLogin) throws Exception {
		log.debug("Request login");
		DataResponse res = new DataResponse();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(accountLogin.getUsername(), accountLogin.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
			res.setStatus(Constant.SUCCESS);
			res.setMessage("Đăng nhập thành công");
			Account account = accountService.getAccountLogin();
			res.setResult(new LoginResponse(jwt, account.getUsername(), account.getFullname(), account.getAuthority()));
			return res;
		} catch (Exception e) {
			res.setStatus(Constant.ERROR);
			res.setMessage("Đăng nhập thất bại");
			return res;
		}
		
	}
	
	@GetMapping("/test")
	public void test() {
		System.out.println("ok");
	}
}
