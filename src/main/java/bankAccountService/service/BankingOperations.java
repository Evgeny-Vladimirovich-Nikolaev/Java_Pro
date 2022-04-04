package bankAccountService.service;

import bankAccountService.dto.AccountDto;
import org.springframework.jdbc.support.lob.LobHandler;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankingOperations {

    AccountDto createAccount(String owner);
    AccountDto createAccount(String owner, BigDecimal balance);
    Optional<AccountDto> getAccount(Long id);
    boolean deposit(Long id, BigDecimal amount);
    boolean withdraw(Long id, BigDecimal amount);
    boolean closeAccount(Long id);
}
