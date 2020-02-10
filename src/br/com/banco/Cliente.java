package br.com.banco;

public class Cliente {

	private String name;
	private String cpf;
	
	public Cliente() {
		
	}
	
	public Cliente(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
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
	
}
