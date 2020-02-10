package br.com.banco;

public class Conta {
	
	private String number;
	private double balance;
	private Cliente customer;
	
	public Conta(String number, Cliente customer) {
		this.number = number;
		this.customer = customer;
	}
	
	public Conta(String number, double balance, Cliente customer) {
		this.number = number;
		this.balance = balance;
		this.customer = customer;
	}
	
	public void creditar(double value) {
		this.balance += value;
	}
	
	public void debitar(double value) {
		if(this.balance >= value) {
			this.balance -= value;
		} else {
			System.out.println("Saldo insuficiente! Operação não realizada.");
		}
	}
	
	public void transferir(Conta account, double value) {
		if(this.balance >= value) {
			this.debitar(value);
			account.creditar(value);
		} else {
			System.out.println("Saldo insuficiente! Operação não realizada.");
		}
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
	
	public Cliente getCustomer() {
		return this.customer;
	}
	
	
	
}
