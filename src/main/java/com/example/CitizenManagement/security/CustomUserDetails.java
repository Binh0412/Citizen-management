/**
 * 
 */
package com.example.CitizenManagement.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.CitizenManagement.entity.Account;

/**
 * @author OS
 * <br> CustomUserDetails
 */
public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Account account;
	
	public CustomUserDetails(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	// ['ROLE_1','ROLE_2']
	/**
	 * @author OS
	 * <br> User có nhiều role
	 */
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		List<String> roles = Common.convertStringToListObject(account.getAuthority());
//		for(String role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
//	}
	
	// ROLE_1
	/**
	 * @author OS
	 * <br> User có 1 role
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(account.getAuthority()));
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
