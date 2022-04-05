package bankAccountService.service;

import bankAccountService.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankingOperations {

    Account createAccount(String owner, BigDecimal balance);
    Optional<Account> getAccount(Long id);
    boolean deposit(Long id, BigDecimal amount);
    boolean withdraw(Long id, BigDecimal amount);
    boolean closeAccount(Long id);
}
