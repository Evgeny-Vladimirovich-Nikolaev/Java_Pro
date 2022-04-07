package bankAccountService.controller.rest;

import bankAccountService.model.Account;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class AccountController {

    private final BankingOperations operations;

    @PostMapping("/createAccount")
    public Account createAccount(@NotEmpty String owner, @Value("0") BigDecimal transfer) {
        return operations.createAccount(owner, transfer).get();
    }

    @GetMapping("/findById")
    public Optional<Account> findById(Long id) {
        return operations.findByAccount(id);
    }

    @GetMapping("/deposit")
    public boolean deposit(Long id, BigDecimal amount) {
        return operations.deposit(id, amount);
    }

    @GetMapping("/withdraw")
    public boolean withdraw(Long id, BigDecimal amount) {
        return operations.withdraw(id, amount);
    }

    @GetMapping("/closeAccount")
    public boolean closeAccount(Long id) {
        return operations.closeAccount(id);
    }
}
