package bankAccountService.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bank_account")
public class AccountDto {

    @Id
    private Long id;
    @Column
    private String owner;
    @Column
    private BigDecimal balance;
}
