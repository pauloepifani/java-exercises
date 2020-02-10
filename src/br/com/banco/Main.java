package br.com.banco;

public class Main {

	public static void main(String[] args) {

		Cliente cliente1 = new Cliente("Paulo", "099.132.654-77");
		Cliente cliente2 = new Cliente("Helo", "888.132.654-88");
		Conta conta1 = new Conta("1", 100, cliente1);
		Conta conta2 = new Conta("2", 100, cliente2);
		System.out.println("\nSaldo Conta1: "+ conta1.getBalanceAsString() + "\nSaldo Conta2: " + conta2.getBalanceAsString());
		conta1.creditar(100.5);
		conta2.debitar(40.3333);
		System.out.println("\nSaldo Conta1: " + conta1.getBalanceAsString() + "\nSaldo Conta2: " + conta2.getBalanceAsString());
		
		conta1.transferir(conta2, 500);
		conta1.transferir(conta2, 150);
		System.out.println("\nSaldo Conta1: " + conta1.getBalanceAsString() + "\nSaldo Conta2: " + conta2.getBalanceAsString());
	}

}
