package bankAccountService.controller.rest;

import bankAccountService.model.Account;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class AccountController {

    private final BankingOperations operations;

    @GetMapping("/createAccount")
    public Optional<Account> createAccount(@RequestParam String owner, @RequestParam @NumberFormat(pattern = "###.###,##") BigDecimal transfer) {
        return operations.createAccount(owner, transfer);
    }

    @GetMapping("/findById")
    public Optional<Account> findById(@RequestParam Long id) {
        return operations.findByAccount(id);
    }

    @GetMapping("/deposit")
    public boolean deposit(@RequestParam Long id, @RequestParam @NumberFormat(pattern = "###.###,##")BigDecimal transfer) {
        System.out.println("sout");
        return operations.deposit(id, transfer);
    }

    @GetMapping("/withdraw")
    public boolean withdraw(@RequestParam Long id, @RequestParam @NumberFormat(pattern = "###.###,##")BigDecimal transfer) {
        System.out.println("sout");
        return operations.withdraw(id, transfer);
    }

    @GetMapping("/closeAccount")
    public boolean closeAccount(@RequestParam Long id) {
        return operations.closeAccount(id);
    }

    @GetMapping("/changeName")
    public Optional<Account>changeName(@RequestParam Long id) {
        return operations.changeName(id);
    }
}