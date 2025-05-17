package com.fmc.ipl.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fmc.ipl.payload.CustomException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<CustomException> resourceNotFoundException(ResourceNotFoundException rex,
			WebRequest web){
		
		CustomException customException=new CustomException();
		customException.setDate(new Date());
		customException.setDetails(web.getDescription(false));
		customException.setMessage(rex.getMessage());
		
		return new  ResponseEntity<CustomException>(customException,HttpStatus.NOT_FOUND) ;
	}
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<CustomException> globalException(Exception rex,
			WebRequest web){
		
		CustomException customException=new CustomException();
		customException.setDate(new Date());
		customException.setDetails(web.getDescription(false));
		customException.setMessage(rex.getMessage());
		
		return new  ResponseEntity<CustomException>(customException,HttpStatus.NOT_FOUND) ;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map <String ,String> error=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((errors) -> {
			
			String fieldName=((FieldError)errors).getField();
			String message=errors.getDefaultMessage();
			error.put(fieldName, message);
		});
		return new ResponseEntity<Object>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
