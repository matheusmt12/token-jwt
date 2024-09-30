package com.example.thymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.Service.UserService;
import com.example.thymeleaf.dto.LoginDTO;
import com.example.thymeleaf.entity.User;
import com.example.thymeleaf.exceptions.PasswordNoFoundException;
import com.example.thymeleaf.exceptions.UsernameNoFoundException;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUSerName(username);
		if(user == null) {
			throw new UsernameNoFoundException("Usuario não encontrado");
		}
		return new UserPrincipal(user);
	}
	
	public void verifyUserCredentials(LoginDTO loginDto) {
		
		UserDetails user = loadUserByUsername(loginDto.getUsername());
		boolean verify = SecurityConfig.passwordEncoder().matches(loginDto.getPassword(), user.getPassword());
		if(!verify) {
			throw new PasswordNoFoundException("Senha inválida");
		}
		
	}
}
