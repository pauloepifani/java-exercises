package br.com.banco.exceptions;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = -4890846417208260389L;
	
	private String message;
	
	public AccountNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
