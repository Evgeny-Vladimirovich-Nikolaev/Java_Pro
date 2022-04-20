package bankAccountService.service;

import bankAccountService.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankingOperations {

    Optional<Account> createAccount(String owner, BigDecimal transfer);
    Optional<Account> findByAccount(Long id);
    boolean deposit(Long id, BigDecimal transfer);
    boolean withdraw(Long id, BigDecimal transfer);
    boolean closeAccount(Long id);

}
