package com.Products.demo.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.Products.demo.Exception.ProductNotFoundException.InvalidProductException;


@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ProductNotFoundException.class)
	    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("error", "Product Not Found");
	        body.put("message", ex.getMessage());
	        body.put("status", HttpStatus.NOT_FOUND.value());
	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InvalidProductException.class)
	    public ResponseEntity<?> handleInvalidProductException(InvalidProductException ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("error", "Invalid Product");
	        body.put("message", ex.getMessage());
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleGeneralException(Exception ex, WebRequest request) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("error", "Internal Server Error");
	        body.put("message", ex.getMessage());
	        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
