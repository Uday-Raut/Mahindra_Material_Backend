package com.ats.mahindrabattery.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;


@RestControllerAdvice
public class ValidationExceptions {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			  MethodArgumentNotValidException ex) {
			    Map<String, String> errorMap = new HashMap<>();
			    ex.getBindingResult().getFieldErrors().forEach((error) -> {
			     //   String fieldName = ((FieldError) error).getField();
			      //  String errorMessage = error.getDefaultMessage();
			        errorMap.put(error.getField(), error.getDefaultMessage());
			    });
			    return errorMap;
			}

}
