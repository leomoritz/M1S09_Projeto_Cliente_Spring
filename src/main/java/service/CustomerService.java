package service;

import model.Customer;
import repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public class CustomerService implements CrudService<Customer> {

    private final CrudRepository<Customer> repository;

    public CustomerService(CrudRepository<Customer> repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(Customer customer) {
        return repository.create(customer);
    }

    @Override
    public Set<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(Integer id, Customer customer) {
        return repository.update(id, customer);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }
}
