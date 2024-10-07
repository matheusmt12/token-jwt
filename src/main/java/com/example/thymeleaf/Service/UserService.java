package com.example.thymeleaf.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dto.UserDTO;
import com.example.thymeleaf.entity.User;
import com.example.thymeleaf.repository.IRepositoryUser;
import com.example.thymeleaf.security.SecurityConfig;

import jakarta.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    private  IRepositoryUser userRepositoryUser;
	
	public User getByUSerName(String username) {
		return userRepositoryUser.findByUsername(username);
	}
	
	@Transactional
	public UserDTO save (UserDTO userDto) {
		User user =  new User();

		user.setPassword(SecurityConfig.passwordEncoder().encode(userDto.getPassword()));
		user.setUsername(userDto.getUsername());
		userRepositoryUser.save(user);
		return new UserDTO(user.getId(),user.getPassword(),user.getUsername());
	}
	
	public List<User> findAll(){
		return userRepositoryUser.findAll();
	}
}
