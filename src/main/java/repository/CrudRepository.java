package repository;

import model.Customer;

import java.util.Optional;
import java.util.Set;

public interface CrudRepository<T> {

    //Create
    public boolean create(T t);
    //Read
    public Set<T> findAll();
    public Optional<Customer> findById(Integer id);
    //Update
    public boolean update(Integer id, T t);
    //Delete
    public boolean delete(Integer id);



}
