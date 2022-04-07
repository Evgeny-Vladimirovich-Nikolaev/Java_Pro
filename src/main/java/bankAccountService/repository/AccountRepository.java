package bankAccountService.repository;

import bankAccountService.model.Account;
import org.hibernate.NonUniqueResultException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    Account save(Account account);
    @Override
    Optional<Account> findById(Long id) throws NonUniqueResultException;
    @Override
    void deleteById(Long aLong);

}
