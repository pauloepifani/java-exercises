package br.com.banco.repository;

import br.com.banco.exceptions.CustomerNotFoundException;
import br.com.banco.model.Customer;

public class CustomerRepository {

	private Customer[] customers;
	private int index;
	public static final int CACHE_LENGTH = 100;
	
	private static CustomerRepository customerRepository = null;

	private CustomerRepository() {
		this.index = 0;
		this.customers = new Customer[CACHE_LENGTH];
	}
	
	public static CustomerRepository getInstance(){
		if(customerRepository == null)
		{
			customerRepository = new CustomerRepository();
		}
		return customerRepository;
	}

	public Customer[] insert(Customer customer) {
		this.customers[index] = customer;
		this.index++;
		return customers;
	}

	public int findByIndex(String cpf) {
		for (int i = 0; i <= this.index - 1; i++) {
			if (customers[i].getCpf().equals(cpf)) {
				return i;
			}
		}
		return -1;
	}

	public boolean exists(String cpf) throws CustomerNotFoundException {
		if (this.findByIndex(cpf) != -1) {
			return true;
		} else {
			throw new CustomerNotFoundException("O cliente de CPF: " + cpf + " não foi encontrado. ");
		}
	}

	public Customer findByCpf(String cpf) throws CustomerNotFoundException {
		if (this.exists(cpf)) {
			int customerIndex = this.findByIndex(cpf);
			return customers[customerIndex];
		} else {
			throw new CustomerNotFoundException("O cliente de CPF: " + cpf + " não foi encontrado. ");
		}
	}

	public void update(Customer customer) throws CustomerNotFoundException {
		if (this.exists(customer.getCpf())) {
			String customerCpf = customer.getCpf();
			int customerIndex = this.findByIndex(customerCpf);
			customers[customerIndex] = customer;
		} else {
			throw new CustomerNotFoundException("O cliente não foi encontrado. ");
		}
	}

	public void deleteByCpf(String cpf) throws CustomerNotFoundException {
		if (this.exists(cpf)) {
			int indexToRemove = this.findByIndex(cpf);
			customers[indexToRemove] = customers[index - 1];
			customers[index - 1] = null;
			index = index - 1;
		} else {
			throw new CustomerNotFoundException("O cliente de CPF: " + cpf + " não foi encontrado. ");
		}
	}
	
	public void getAll() {
		System.out.println("\n---Clientes Cadastrados no Banco Recode---\n");
		for(Customer customer : customers) {
			if(customer != null) {
				System.out.println(customer.toString());
			}
		}
	}
	
	
}
