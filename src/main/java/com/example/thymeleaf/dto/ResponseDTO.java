package com.example.thymeleaf.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ResponseDTO<T> {

    	
	@Getter
	public List<String> message;
	@Getter
	public T data;
	
	public ResponseDTO(List<String> message, T data) {
		this.message = message;
		this.data = data;
	}

	public ResponseDTO(String message, T data) {
		this.message = Arrays.asList(message);
		this.data = data;
	}
	
	
}
