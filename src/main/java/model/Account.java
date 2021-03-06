package model;

import lombok.*;
import model.enums.AccountType;
import utils.UtilGeradorId;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
public class Account implements Comparable<Account>{

    private Integer id = UtilGeradorId.generateSequenceIdAccount();

    @NonNull
    private final Customer customer;

    @NonNull
    private AccountType accountType;

    private Double balance = 0.0;

    public boolean withdraw(Double value){
        if(value > 0.0 && value <= this.balance){
            this.balance -= value;
            return true;
        }
        return false;
    }

    public boolean deposit(Double value){
        if(value > 0.0){
            this.balance += value;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Conta: " + id + " | " + "Cliente: " + customer.getName() + " | " + "Tipo Conta: " + accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Account o) {
        return this.id.compareTo(o.id);
    }
}
