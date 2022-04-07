package bankAccountService.service.impl;

import bankAccountService.model.Account;
import bankAccountService.repository.AccountRepository;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    public Optional<Account> createAccount(String owner, @Value("0") BigDecimal transfer) {
        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(transfer);
        repository.save(account);
        return Optional.of(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByAccount(Long id) {
        return  Optional.of(repository.findById(id)).orElseThrow();
    }

    @Override
    public boolean deposit(Long id, BigDecimal transfer) {
        Optional<Account> accountDto = findByAccount(id);
        BigDecimal balance = accountDto.get().getBalance();
        accountDto.get().setBalance(balance.add(transfer));
        repository.save(accountDto.get());
        return true;
    }

    @Override
    public boolean withdraw(Long id, BigDecimal transfer) {
        Optional<Account> accountDto = findByAccount(id);
        BigDecimal balance = accountDto.get().getBalance();
        if(balance.doubleValue() >= transfer.doubleValue()){
            accountDto.get().setBalance(balance.subtract(transfer));
            return true;
        }
        return false;
    }

    @Override
    public boolean closeAccount(Long id) {
        Optional<Account> accountDto = findByAccount(id);
        accountDto.get().setBalance(new BigDecimal(0));
        repository.delete(accountDto.get());
        return true;
    }
}
