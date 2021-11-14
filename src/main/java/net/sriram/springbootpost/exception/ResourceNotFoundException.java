package net.sriram.springbootpost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -911265148508790608L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}