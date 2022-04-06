package bankAccountService.service.impl;

import bankAccountService.model.Account;
import bankAccountService.repository.AccountRepository;
import bankAccountService.service.BankingOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
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
    public Account createAccount(String owner, @Value("0") BigDecimal amount) {
        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(amount);
        repository.save(account);
        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getAccount(Long id) {
        return  Optional.of(repository.findById(id)).orElseThrow();
    }

    @Override
    public boolean deposit(Long id, BigDecimal transfer) {
        Optional<Account> accountDto = getAccount(id);
        BigDecimal balance = accountDto.get().getBalance();
        accountDto.get().setBalance(balance.add(transfer));
        repository.save(accountDto.get());
        return true;
    }

    @Override
    public boolean withdraw(Long id, BigDecimal transfer) {
        Optional<Account> accountDto = getAccount(id);
        BigDecimal balance = accountDto.get().getBalance();
        if(balance.doubleValue() >= transfer.doubleValue()){
            accountDto.get().setBalance(balance.subtract(transfer));
            return true;
        }
        return false;
    }

    @Override
    public boolean closeAccount(Long id) {
        System.out.println("sout");
        Optional<Account> accountDto = getAccount(id);
        accountDto.get().setBalance(new BigDecimal(0));
        repository.delete(accountDto.get());
        return true;
    }
}
