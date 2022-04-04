package bankAccountService.service.impl;

import bankAccountService.dto.AccountDto;
import bankAccountService.service.BankingOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BankingOperationsImpl implements BankingOperations {
    @Override
    public AccountDto createAccount(String owner) {
        return null;
    }

    @Override
    public AccountDto createAccount(String owner, BigDecimal balance) {
        return null;
    }

    @Override
    public Optional<AccountDto> readAccount(Long id) {
        return null;
    }

    @Override
    public boolean deposit(Long id, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean withdraw(Long id, BigDecimal amount) {
        return false;
    }
}
