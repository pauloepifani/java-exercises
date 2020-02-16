package br.com.banco.model;

public class Customer {

	private String name;
	private String cpf;
	private CustomerType customerType;
	
	public enum CustomerType {
		
		VIP ("Vip"),
		CLASS ("Class"),
		ESPECIAL ("Especial");
		
		private String description;

		private CustomerType(String type) {
			this.description = type;
		}
		
		public String getDescricao() {
			return this.description;
		}
	}

	public Customer() {

	}

	public Customer(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public Customer(String name, String cpf, String customerType) {
		this.name = name;
		this.cpf = cpf;
		this.customerType = setCustomerType(customerType);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return this.cpf;
	}

	public CustomerType getCustomerType() {
		return this.customerType;
	}

	public CustomerType setCustomerType(String informedType) {
		switch(informedType.toUpperCase()) {
		case "ESPECIAL":
			this.customerType = CustomerType.ESPECIAL;
			break;
		case "VIP":
			this.customerType = CustomerType.VIP;
			break;
		case "CLASS":
		default:
			this.customerType = CustomerType.CLASS;
			break;
		}
		return this.customerType;
	}

	@Override
	public String toString() {
		return "Nome: " + name + "\nCPF: " + cpf + "\nTipo de Cliente: " + customerType.description + "\n";
	}

}
