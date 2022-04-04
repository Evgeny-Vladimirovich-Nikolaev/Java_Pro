package bankAccountService.service;

import bankAccountService.dto.AccountDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankingOperations {

    AccountDto createAccount(String owner);
    AccountDto createAccount(String owner, BigDecimal balance);
    Optional<AccountDto> readAccount(Long id);
    boolean deposit(Long id, BigDecimal amount);
    boolean withdraw(Long id, BigDecimal amount);
}
