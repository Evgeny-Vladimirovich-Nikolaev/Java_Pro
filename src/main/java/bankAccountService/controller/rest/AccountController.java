package bankAccountService.controller.rest;

import bankAccountService.model.Account;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class AccountController {

    private final BankingOperations operations;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(@RequestBody Account account) {
        operations.createAccount(account.getOwner(), account.getBalance());
        return account;
    }

    @GetMapping("/findById")
    public Optional<Account> findById(@RequestParam Long id) {
        return operations.findByAccount(id);
    }

    @GetMapping("/deposit")
    public boolean deposit(@RequestParam Long id,
                        @RequestParam @NumberFormat(pattern = "###.###,##") BigDecimal transfer) {
        return operations.deposit(id, transfer);
    }

    @GetMapping("/withdraw")
    public boolean withdraw(@RequestParam long id,
                            @RequestParam @NumberFormat(pattern = "###.###,##")BigDecimal transfer) {
        return operations.withdraw(id, transfer);
    }

    @GetMapping("/closeAccount")
    public boolean closeAccount(@RequestParam Long id) {
        return operations.closeAccount(id);
    }
}
