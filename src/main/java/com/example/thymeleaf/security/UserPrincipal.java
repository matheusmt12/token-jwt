package com.example.thymeleaf.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.thymeleaf.entity.User;

public class UserPrincipal implements UserDetails {

    
	
	private String password;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserPrincipal(User user) {
		this.password = user.getPassword();
		this.username = user.getUsername();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	
	}@Override
	public String getUsername() {
		return this.username;
	
	}@Override
	public boolean isAccountNonExpired() {
		return true;
	
	}@Override
	public boolean isAccountNonLocked() {
		return true;
	
	}@Override
	public boolean isCredentialsNonExpired() {
		return true;
	
	}@Override
	public boolean isEnabled() {
		return true;
	}
	
}
