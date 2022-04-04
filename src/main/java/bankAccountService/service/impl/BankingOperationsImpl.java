package bankAccountService.service.impl;

import bankAccountService.dto.AccountDto;
import bankAccountService.repository.AccountRepository;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BankingOperationsImpl implements BankingOperations {

    private final AccountRepository repository;

    @Override
    public AccountDto createAccount(String owner) {
        return null;
    }

    @Override
    public AccountDto createAccount(String owner, BigDecimal balance) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountDto> getAccount(Long id) {
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

    @Override
    public boolean closeAccount(Long id) {
        return false;
    }
}
