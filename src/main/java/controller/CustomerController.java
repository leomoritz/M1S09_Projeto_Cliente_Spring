package controller;

import model.Customer;
import service.CustomerService;

public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    public boolean create(Customer customer) {
        return service.create(customer);
    }

}
