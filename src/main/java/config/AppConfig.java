package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repository.CustomerRepository;
import service.CustomerService;

@Configuration
@ComponentScan("dev")
public class AppConfig {

    @Bean("customerRepository")
    public CustomerRepository getCustomerRepository(){
        return new CustomerRepository();
    }

    @Bean("customerService")
    public CustomerService getCustomerService(){
        return new CustomerService(getCustomerRepository());
    }
}
