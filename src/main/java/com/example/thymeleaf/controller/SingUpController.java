package com.example.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.Service.UserService;
import com.example.thymeleaf.dto.UserDTO;

@RestController
@RequestMapping("/sing-up")
public class SingUpController {

    private UserService userService;
	
	@Autowired
	public SingUpController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	
	@PostMapping
	public ResponseEntity post(@RequestBody UserDTO user) {
		try {
			return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
