package com.example.thymeleaf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.Service.UserService;
import com.example.thymeleaf.dto.LoginDTO;
import com.example.thymeleaf.dto.TokenDTO;
import com.example.thymeleaf.security.CustomUserDetailService;
import com.example.thymeleaf.security.JwtService;




@RestController
@RequestMapping("/login")
public class UserController {

	private UserService userService;
	private CustomUserDetailService customUser;
	private JwtService jwtService;
	
	
	@Autowired
	public UserController(UserService userService, JwtService jwtService, CustomUserDetailService customUser) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.customUser = customUser;
	}

    
	@Value("${security.jwt.expiration}")
	private String expiration;
	@GetMapping
	public ResponseEntity get() {
		return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
		
	}

	@PostMapping()
	public ResponseEntity post( @RequestBody LoginDTO login) {
		try {
			customUser.verifyUserCredentials(login);
			
			String token = jwtService.generateToken(login.getUsername());
			return new ResponseEntity<>(new TokenDTO(token,expiration),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}