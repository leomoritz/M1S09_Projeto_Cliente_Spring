package main;

import config.AppConfig;
import controller.CustomerController;
import model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CrudService;
import service.CustomerService;
import utils.UtilValidaCpf;

public class AppMain {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerService service = applicationContext.getBean("customerService", CustomerService.class);

        Customer customer = new Customer("Leônidas Guilherme Moritz Pereira Rosa", "09874553979");
        Customer customer2 = new Customer("Bruna de Oliveira Rosa", "09988020937");

        System.out.println("Cadastrando Clientes: ");

        if (UtilValidaCpf.validaCfp(customer.getCpf()) && service.create(customer)) {
            System.out.println("Cliente " + customer.getName() + " cadastrado com sucesso!");
        }

        if (UtilValidaCpf.validaCfp(customer2.getCpf()) && service.create(customer2)) {
            System.out.println("Cliente " + customer2.getName() + " cadastrado com sucesso!");
        }

        System.out.println("\n");
        System.out.println("Listagem de Clientes: ");
        service.findAll().forEach(customerAux -> System.out.println(customerAux.toString()));

        System.out.println("\n");
        System.out.println("Busca de Cliente Pelo ID: ");
        System.out.println(service.findById(1).get().toString());

        System.out.println("\n");
        System.out.println("Atualizando pelo ID: ");
        customer.setName("Leônidas G. M. P. Rosa"); //Alterando apenas o nome
        if ((service.update(1, customer))) { // Enviando para atualização
            System.out.println("Dados do cliente atualizado com sucesso! Novos dados: ");
            System.out.println(service.findById(customer.getId()).get().toString());
        }

        System.out.println("\n");
        System.out.println("Deletando Cliente Pelo ID: ");
        String clienteDeletado = customer.getName();
        if (service.delete(1)) {
            System.out.println("Cliente " + clienteDeletado + " deletado com sucesso!");
        }

        System.out.println("\n");
        System.out.println("Listagem de Clientes: ");
        service.findAll().forEach(customerAux -> System.out.println(customerAux.toString()));

    }
}
