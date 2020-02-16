package br.com.banco.exceptions;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 8719912908384778058L;

	private String message;
	
	public CustomerNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
