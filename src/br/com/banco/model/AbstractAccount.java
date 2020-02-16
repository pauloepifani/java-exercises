package br.com.banco.model;

import br.com.banco.exceptions.InsufficientFundsException;

public abstract class AbstractAccount {

	protected String number;
	protected double balance;
	protected Customer customer;

	public AbstractAccount(String number, Customer customer) {
		this.number = number;
		this.customer = customer;
	}

	public AbstractAccount() {

	}

	public AbstractAccount(String number, double balance, Customer customer) {
		this(number, customer);
		this.balance = balance;
	}

	public void credit(double value) {
		this.balance += value;
	}

	public abstract void debit(double value) throws InsufficientFundsException;

	public void transfer(AbstractAccount account, double value) throws InsufficientFundsException {
			this.debit(value);
			account.credit(value);
	}

	public String getNumber() {
		return this.number;
	}

	public double getBalance() {
		return this.balance;
	}

	public String getBalanceAsString() {
		String balanceString = "R$" + String.format("%.2f", this.balance);
		return balanceString;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public String toString() {
		return "Número: " + number + "\nSaldo: " + getBalanceAsString() + "\nCliente: " + customer.getCpf() + "\n";
	}
	
	
	
}
