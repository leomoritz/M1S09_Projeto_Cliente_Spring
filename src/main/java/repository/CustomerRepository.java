package repository;

import model.Customer;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class CustomerRepository implements CrudRepository<Customer> {

    private static final Set<Customer> REPOSITORY = new TreeSet<>();

    @Override
    public boolean create(Customer customer) {
        return REPOSITORY.add(customer);
    }

    @Override
    public Set<Customer> findAll() {
        return REPOSITORY;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return REPOSITORY.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    @Override
    public boolean update(Integer id, Customer customer) {
        if(findById(id).isEmpty()){
            return false;
        }

        // Lógica para sobrescrever na lista o mesmo identificador com as novas informações atualizadas
        delete(id);
        customer.setId(id);
        create(customer);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Customer> customer = findById(id);

        if (customer.isEmpty()) {
            return false;
        }
        return REPOSITORY.remove(customer.get());

    }
}
