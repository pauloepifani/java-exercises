package br.com.banco.view;

import java.util.Scanner;

import br.com.banco.exceptions.CustomerNotFoundException;
import br.com.banco.exceptions.AccountNotFoundException;
import br.com.banco.exceptions.InsufficientFundsException;
import br.com.banco.model.Customer;
import br.com.banco.model.AbstractAccount;
import br.com.banco.model.CheckingAccount;
import br.com.banco.repository.CustomerRepository;
import br.com.banco.repository.AccountRepository;

public class ApplicationMenu {

	static Scanner scan = new Scanner(System.in);

	public static void start() {

		System.out.println("Bem vindo ao Banco Recode!\n");
		System.out.println("Escolha uma dentre as opções listadas abaixo: \n" 
							+ "\n 1 - CRUD Cliente"
							+ "\n 2 - CRUD Conta" 
							+ "\n 3 - Sair\n");
		int firstChoice = scan.nextInt();

		switch (firstChoice) {
		case 1:
			System.out.println("\nCRUD Cliente: \n" 
							+ "\n 1 - Cadastrar" 
							+ "\n 2 - Imprimir todos"
							+ "\n 3 - Atualizar Dados" 
							+ "\n 4 - Remover\n"
							+ "\n 5 - Voltar\n");
			break;
		case 2:
			System.out.println("\nCRUD Conta: \n" 
							+ "\n 1 - Cadastrar" 
							+ "\n 2 - Imprimir todas" 
							+ "\n 3 - Sacar"
							+ "\n 4 - Depositar" 
							+ "\n 5 - Transferir" 
							+ "\n 6 - Remover\n"
							+ "\n 7 - Voltar\n");
			break;
		case 3:
			ApplicationMenu.close();
			break;
		default:
			System.out.println("Opção escolhida não existe!\nRedirecionando para menu inicial...");
			ApplicationMenu.start();
			break;
		}
		int secondChoice = scan.nextInt();
		switch (firstChoice) {
		case 1:

			switch(secondChoice) {
			case 1:
				System.out.println("\nNome: ");
				String name = scan.next();
				System.out.println("CPF: ");
				String cpf = scan.next();
				System.out.println("Tipo de Cliente: ");
				String tipoInformado = scan.next();
				Customer cliente = new Customer(name, cpf, tipoInformado);
				CustomerRepository.getInstance().insert(cliente);
				break;
				
			case 2:
				CustomerRepository.getInstance().getAll();
				break;
				
			case 3:
				System.out.println("Informe o CPF do cliente: ");
				String cpfAtualizar = scan.next();
				try {
					CustomerRepository.getInstance().findByCpf(cpfAtualizar);
					System.out.println("Novo nome: ");
					String novoNome = scan.next();
					System.out.println("Novo tipo de Cliente: ");
					String tipoCliente = scan.next();
					Customer clienteNovo = new Customer(novoNome, cpfAtualizar, tipoCliente);
					CustomerRepository.getInstance().update(clienteNovo);
					
				} catch (CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 4:
				System.out.println("Informe o CPF do cliente: ");
				String cpfRemover = scan.next();
				try {
					CustomerRepository.getInstance().deleteByCpf(cpfRemover);
				} catch (CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 5:
				ApplicationMenu.start();
				break;
			
			default:
				System.out.println("\nOpção escolhida não existe!\nRedirecionando para menu inicial...\n");
				ApplicationMenu.start();
				break;
			}
			break;

		case 2:
			switch (secondChoice) {
			case 1:
				System.out.println("\nNúmero: ");
				String number = scan.next();
				System.out.println("Cliente CPF: ");
				String cpf = scan.next();
				Customer cliente;
				try {
					cliente = CustomerRepository.getInstance().findByCpf(cpf);
					CheckingAccount conta = new CheckingAccount(number, cliente);
					AccountRepository.getInstance().insert(conta);
				} catch (CustomerNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 2:
				AccountRepository.getInstance().getAll();
				break;
				
			case 3:
				System.out.print("Informe o número da conta: ");
				String numeroContaSacar = scan.next();
				try {
					AbstractAccount conta = AccountRepository.getInstance().procurar(numeroContaSacar);
					System.out.print("Informe o valor para saque: ");
					double valorSaque = scan.nextDouble();
					try {
						conta.debit(valorSaque);
						System.out.println("\nOperação realizada com sucesso!\n");
					} catch (InsufficientFundsException e) {
						System.out.println(e.getMessage());
					} finally {
						System.out.println(conta.toString());
					}
				} catch (AccountNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
			case 4:
				System.out.print("Informe o número da conta: ");
				String numeroContaDepositar = scan.next();
				try {
					AbstractAccount conta = AccountRepository.getInstance().procurar(numeroContaDepositar);
					System.out.print("Informe o valor para depósito: ");
					double valorDeposito = scan.nextDouble();
					conta.credit(valorDeposito);
				} catch (AccountNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
			case 5:
				System.out.print("Informe o número da sua conta: ");
				String numeroContaSaida = scan.next();
				try {
					AbstractAccount contaSaida = AccountRepository.getInstance().procurar(numeroContaSaida);
					System.out.print("Informe o número da conta de destino: ");
					String numeroContaDestino = scan.next();
					AbstractAccount contaDestino = AccountRepository.getInstance().procurar(numeroContaDestino);
					System.out.print("Informe o valor para transferência: ");
					double valorTransferencia = scan.nextDouble();
					contaSaida.transfer(contaDestino, valorTransferencia);
				} catch (AccountNotFoundException | InsufficientFundsException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
			case 6:
				System.out.print("Informe o número da conta: ");
				String numeroContaRemover = scan.next();
				try {
					AccountRepository.getInstance().delete(numeroContaRemover);
				} catch (AccountNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
			case 7:
				ApplicationMenu.start();
				break;

			default:
				System.out.println("\nOpção escolhida não existe!\nRedirecionando para menu inicial...\n");
				ApplicationMenu.start();
				break;
			}
			break;

		default:
			System.out.println("\nOpção escolhida não existe!\nRedirecionando para menu inicial...\n");
			ApplicationMenu.start();
			break;
		}
		ApplicationMenu.continuar();
	}

	public static void close() {
		System.out.println("\nAté a próxima :)");
		scan.close();
		System.exit(0);
	}

	public static void continuar() {
		System.out.println("\nDeseja realizar outra operação? ");
		String resposta = scan.next();
		char primeiraLetra = resposta.toUpperCase().charAt(0);
		if (primeiraLetra == 'N') {
			ApplicationMenu.close();
		} else {
			ApplicationMenu.start();
		}
	}

}
