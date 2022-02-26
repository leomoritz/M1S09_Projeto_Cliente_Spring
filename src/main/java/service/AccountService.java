package service;

import model.Account;
import model.Customer;
import repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public class AccountService implements CrudService<Account>{

    private CrudRepository<Account> repository;

    public AccountService(CrudRepository<Account> repository) {
        this.repository = repository;
    }


    @Override
    public boolean create(Account account) {
        return repository.create(account);
    }

    @Override
    public Set<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(Integer id, Account account) {
        return repository.update(id, account);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }
}
