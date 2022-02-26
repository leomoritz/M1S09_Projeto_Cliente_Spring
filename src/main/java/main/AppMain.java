package main;

import config.AppConfig;
import model.Account;
import model.Customer;
import model.enums.AccountType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountService;
import service.CustomerService;
import utils.UtilValidaCpf;

public class AppMain {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerService customerService = applicationContext.getBean("customerService", CustomerService.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);

        Customer customer1 = new Customer("Leônidas Guilherme Moritz Pereira Rosa", "09874553979");
        Customer customer2 = new Customer("Bruna de Oliveira Rosa", "09988020937");


        /*
         * Operações CRUD com o cliente
         */

        System.out.println("\n");
        System.out.println("Cadastrando Clientes: ");

        if (UtilValidaCpf.validaCfp(customer1.getCpf()) && customerService.create(customer1)) {
            System.out.println("Cliente " + customer1.getName() + " cadastrado com sucesso!");
        }

        if (UtilValidaCpf.validaCfp(customer2.getCpf()) && customerService.create(customer2)) {
            System.out.println("Cliente " + customer2.getName() + " cadastrado com sucesso!");
        }

        System.out.println("\n");
        System.out.println("Listagem de Clientes: ");
        customerService.findAll().forEach(customer -> System.out.println(customer.toString()));

        System.out.println("\n");
        System.out.println("Busca de Cliente Pelo ID: ");
        System.out.println(customerService.findById(1).get().toString());

        System.out.println("\n");
        System.out.println("Atualizando pelo ID: ");
        customer1.setName("Leônidas G. M. P. Rosa"); //Alterando apenas o nome
        if ((customerService.update(1, customer1))) { // Enviando para atualização
            System.out.println("Dados do cliente atualizado com sucesso! Novos dados: ");
            System.out.println(customerService.findById(customer1.getId()).get().toString());
        }

        System.out.println("\n");
        System.out.println("Deletando Cliente Pelo ID: ");
        String clienteDeletado = customer1.getName();
        if (customerService.delete(1)) {
            System.out.println("Cliente " + clienteDeletado + " deletado com sucesso!");
        }

        System.out.println("\n");
        System.out.println("Listagem de Clientes: ");
        customerService.findAll().forEach(customer -> System.out.println(customer.toString()));

        /*
         * Operações CRUD com a conta
         */
        Account account1 = new Account(customer1, AccountType.CURRENT_ACCOUNT);
        Account account2 = new Account(customer2, AccountType.SAVINGS_ACCOUNT);

        System.out.println("\n");
        System.out.println("Cadastrando Contas: ");

        if (accountService.create(account1)) {
            System.out.println("Conta do cliente " + account1.getCustomer().getName() + " cadastrado com sucesso!");
        }

        if (accountService.create(account2)) {
            System.out.println("Conta do cliente " + account2.getCustomer().getName() + " cadastrado com sucesso!");
        }

        System.out.println("\n");
        System.out.println("Listagem de Contas: ");
        accountService.findAll().forEach(account -> System.out.println(account.toString()));

        System.out.println("\n");
        System.out.println("Busca de Conta Pelo ID: ");
        System.out.println(accountService.findById(2).get().toString());

        System.out.println("\n");
        System.out.println("Atualizando pelo ID: ");
        account2.setAccountType(AccountType.CURRENT_ACCOUNT); //Alterando apenas o tipo de conta
        if ((accountService.update(account2.getId(), account2))) { // Enviando para atualização
            System.out.println("Dados da conta atualizado com sucesso! Novos dados: ");
            System.out.println(accountService.findById(account2.getId()).get().toString());
        }

        System.out.println("\n");
        System.out.println("Deletando Conta Pelo ID: ");
        String contaDeletada = account2.getCustomer().getName();
        if (accountService.delete(2)) {
            System.out.println("Conta do cliente " + contaDeletada + " deletado com sucesso!");
        }

        System.out.println("\n");
        System.out.println("Listagem de Contas: ");
        accountService.findAll().forEach(account -> System.out.println(account.toString()));

        /*
         * Testando as operações de saque e depósito da conta
         * */

        System.out.println("\n");
        //Depositando
        if (account1.deposit(1000.00)) {
            System.out.println("Depósito de R$10000 realizado com sucesso!");
        }

        //Sacando
        if (account1.withdraw(500.00)) {
            System.out.println("Saque de R$50 realizado com sucesso!");
        }

        //Consultando saldo após saque
        System.out.println("Saldo atual após o saque: R$" + account1.getBalance());

        //Tentando um saque maior que o valor da conta
        if (account1.withdraw(2000.00)) {
            System.out.println("Saque realizado com sucesso!");
        }else{
            System.out.println("Não foi possível realizar o saque. Saldo insuficiente!");
        }

        //Testando um saque <= 0
        if (account1.withdraw(0.0)) {
            System.out.println("Saque realizado com sucesso!");
        }else{
            System.out.println("Não foi possível realizar o saque. O saque precisa ser maior que zero!");
        }

        //Tentando um depósito negativo
        if (account1.deposit(-1.0)) {
            System.out.println("Depósito realizado com sucesso!");
        }else{
            System.out.println("Não foi possível realizar o depósito. O depósito precisa ser maior que zero!");
        }

    }
}
