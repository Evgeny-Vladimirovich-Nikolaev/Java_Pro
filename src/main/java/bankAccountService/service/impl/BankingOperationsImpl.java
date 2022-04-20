package bankAccountService.service.impl;

import bankAccountService.model.Account;
import bankAccountService.repository.AccountRepository;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BankingOperationsImpl implements BankingOperations {

    private final AccountRepository repository;

    @Override
    public Optional<Account> createAccount(String owner, BigDecimal transfer) {
        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(transfer);
        repository.save(account);
        return Optional.of(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByAccount(Long id) {
        return  Optional.of(repository
                .findById(id))
                .orElseThrow(() -> new RuntimeException("счет №" + id + " не найден"));
    }

    @Override
    public boolean deposit(Long id, BigDecimal transfer) {
        return findByAccount(id)
                .map(account -> {
                    BigDecimal balance = account.getBalance();
                    account.setBalance(balance.add(transfer));
                    repository.save(account);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean withdraw(Long id, BigDecimal transfer) {
        return findByAccount(id)
                .map(account -> {
                    BigDecimal balance = account.getBalance();
                    account.setBalance(balance.subtract(transfer));
                    repository.save(account);
                    return true;
                }).orElse(false);
    }

    @Override
    public boolean closeAccount(Long id) {
        return findByAccount(id)
                .map(account -> {
                    account.setBalance(new BigDecimal(0));
                    repository.delete(account);
                    return true;
                }).orElse(false);
    }

}
