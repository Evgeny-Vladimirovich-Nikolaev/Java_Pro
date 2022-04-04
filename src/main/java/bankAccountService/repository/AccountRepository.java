package bankAccountService.repository;

import bankAccountService.dto.AccountDto;
import cityDirectory.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.NonUniqueResultException;
import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountDto, Long> {
//    AccountDto createAccountDto(String owner);
//    AccountDto createAccountDto(String owner, BigDecimal balance);
//    Optional<AccountDto> findById(Long id) throws NonUniqueResultException;
//    Optional<AccountDto> updateById(Long id) throws NonUniqueResultException;
}
