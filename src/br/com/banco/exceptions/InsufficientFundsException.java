package br.com.banco.exceptions;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 6620892906188988896L;
	
	private String message;
	
	public InsufficientFundsException(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
