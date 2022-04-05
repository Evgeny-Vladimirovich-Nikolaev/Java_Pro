package bankAccountService.repository;

import bankAccountService.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    Account createAccountDto(String owner);
//    Account createAccountDto(String owner, BigDecimal balance);
//    Optional<Account> findById(Long id) throws NonUniqueResultException;
//    Optional<Account> updateById(Long id) throws NonUniqueResultException;
}
