package br.com.banco.repository;

import br.com.banco.exceptions.AccountNotFoundException;
import br.com.banco.model.AbstractAccount;

public class AccountRepository {

	private AbstractAccount[] accounts;
	private int index;
	public static final int CACHE_LENGTH = 100;
	
	private static AccountRepository accountRepository = null; 

	private AccountRepository() {
		this.index = 0;
		this.accounts = new AbstractAccount[CACHE_LENGTH];
	}
	
	public static AccountRepository getInstance() {
		if(accountRepository == null) {
			accountRepository = new AccountRepository();
		}
			return accountRepository;

	}

	public AbstractAccount[] insert(AbstractAccount account) {
		this.accounts[index] = account;
		this.index++;
		return accounts;
	}

	public int findByIndex(String number) {
		for (int i = 0; i <= this.index - 1; i++) {
			if (accounts[i].getNumber().equals(number)) {
				return i;
			}
		}
		return -1;
	}

	public boolean exists(String number) throws AccountNotFoundException {
		if (this.findByIndex(number) != -1) {
			return true;
		} else {
			throw new AccountNotFoundException("A conta de número: " + number + " não foi encontrada.");
		}
	}

	public AbstractAccount procurar(String number) throws AccountNotFoundException {
		if (this.exists(number)) {
			int accountIndex = this.findByIndex(number);
			return accounts[accountIndex];
		} else {
			throw new AccountNotFoundException("A conta de número: " + number + " não foi encontrada.");
		}
	}

	public void update(AbstractAccount account) throws AccountNotFoundException {
		if (this.exists(account.getNumber())) {
			int accountIndex = this.findByIndex(account.getNumber());
			accounts[accountIndex] = account;
		} else {
			throw new AccountNotFoundException("A conta informada não foi encontrada.");
		}
	}

	public void delete(String number) throws AccountNotFoundException {
		if (this.exists(number)) {
			int indexToRemove = this.findByIndex(number);
			accounts[indexToRemove] = accounts[indexToRemove - 1];
			accounts[indexToRemove - 1] = null;
			index = index - 1;
		} else {
			throw new AccountNotFoundException("A conta de número: " + number + " não foi encontrada.");
		}
	}

	public void getAll() {
		System.out.println("\n---Contas Cadastradas no Banco Recode---\n");
		for (AbstractAccount account : accounts) {
			if (account != null) {
				System.out.println(account.toString());
			}
		}
	}

}
