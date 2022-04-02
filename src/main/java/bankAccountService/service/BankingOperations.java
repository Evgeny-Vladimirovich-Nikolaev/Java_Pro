package bankAccountService.service;

import bankAccountService.dto.AccountDto;

import java.math.BigDecimal;

public interface BankingOperations {

    AccountDto createAccount (String owner);
    AccountDto createAccount (String owner, BigDecimal balance);

}
