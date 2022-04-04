package bankAccountService.controller.rest;

import bankAccountService.dto.AccountDto;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class AccountController {
    public Optional<AccountDto> createAccount(String owner) {
        return Optional.empty();
    }

    public Optional<AccountDto> createAccount(String owner, BigDecimal amount) {
        return Optional.empty();
    }

    public Optional<AccountDto> findById(Long id) {
        return Optional.empty();
    }

    public boolean deposit(Long id, BigDecimal amount) {
        return false;
    }

    public boolean withdraw(Long id, BigDecimal amount) {
        return false;
    }

    public boolean closeAccount(Long id) {
        return false;
    }
}
