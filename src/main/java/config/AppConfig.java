package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repository.AccountRepository;
import repository.CustomerRepository;
import service.AccountService;
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

    @Bean("accountRepository")
    public AccountRepository getAccountRepository(){
        return new AccountRepository();
    }

    @Bean("accountService")
    public AccountService getAccountService(){
        return new AccountService(getAccountRepository());
    }

}
