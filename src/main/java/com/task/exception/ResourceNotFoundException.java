package com.task.exception;

public class ResourceNotFoundException  extends RuntimeException{

	public ResourceNotFoundException() {
		super("Product Id Not Found");
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
