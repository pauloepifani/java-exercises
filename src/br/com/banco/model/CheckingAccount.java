package br.com.banco.model;

import br.com.banco.exceptions.InsufficientFundsException;

public class CheckingAccount extends AbstractAccount {
	
	public CheckingAccount(String number, Customer customer) {
		super(number, customer);
	}
	
	@Override
	public void debit(double value) throws InsufficientFundsException {
		if (super.getBalance() >= value) {
			super.balance -= value;
		} else {
			throw new InsufficientFundsException("Saldo insuficiente! Operação não realizada.");
		}
	}

}
