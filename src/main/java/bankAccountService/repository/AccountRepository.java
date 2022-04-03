package bankAccountService.repository;

import bankAccountService.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDto, Long> {
}
