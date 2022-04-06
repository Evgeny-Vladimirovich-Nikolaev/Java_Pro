package bankAccountService.controller.rest;

import bankAccountService.model.Account;
import bankAccountService.service.BankingOperations;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
//@NoArgsConstructor
@RequiredArgsConstructor
public class AccountController {

    private final BankingOperations operations;

    public Optional<Account> createAccount(@NotEmpty String owner, @Value("0") BigDecimal amount) {
        return Optional.empty();
    }

    public Optional<Account> findById(Long id) {
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
