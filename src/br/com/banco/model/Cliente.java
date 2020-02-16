package br.com.banco.model;

public class Cliente {

	private String name;
	private String cpf;
	private TipoCliente tipoCliente;
	
	public enum TipoCliente {
		
		VIP ("Vip"),
		CLASS ("Class"),
		ESPECIAL ("Especial");
		
		private String descricao;

		private TipoCliente(String tipo) {
			this.descricao = tipo;
		}
		
		public String getDescricao() {
			return this.descricao;
		}
	}

	public Cliente() {

	}

	public Cliente(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public Cliente(String name, String cpf, String tipoCliente) {
		this.name = name;
		this.cpf = cpf;
		this.tipoCliente = setTipoCliente(tipoCliente);
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

	public TipoCliente getTipoCliente() {
		return this.tipoCliente;
	}

	public TipoCliente setTipoCliente(String tipoInformado) {
		switch(tipoInformado.toUpperCase()) {
		case "ESPECIAL":
			this.tipoCliente = TipoCliente.ESPECIAL;
			break;
		case "VIP":
			this.tipoCliente = TipoCliente.VIP;
			break;
		case "CLASS":
		default:
			this.tipoCliente = TipoCliente.CLASS;
			break;
		}
		return this.tipoCliente;
	}

	@Override
	public String toString() {
		return "Nome: " + name + "\nCPF: " + cpf + "\nTipo de Cliente: " + tipoCliente.descricao + "\n";
	}

}
