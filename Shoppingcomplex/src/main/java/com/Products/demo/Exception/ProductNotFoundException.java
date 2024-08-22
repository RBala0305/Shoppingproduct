package com.Products.demo.Exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public class InvalidProductException extends RuntimeException{
		
		public InvalidProductException(String message) {
			super (message);
		}
		
		
	}
}
