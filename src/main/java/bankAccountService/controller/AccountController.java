package bankAccountService.controller;

import bankAccountService.dto.AccountDto;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountController {

    Optional<AccountDto> createAccount(String owner);

    Optional<AccountDto> createAccount(String owner, BigDecimal amount);

    Optional<AccountDto> findById(Long id);

}
