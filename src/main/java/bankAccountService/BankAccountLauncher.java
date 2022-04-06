package bankAccountService;

import bankAccountService.service.BankingOperations;
import bankAccountService.service.impl.BankingOperationsImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class BankAccountLauncher {
    public static void main(String[] args) {
        SpringApplication.run(BankAccountLauncher.class, args);
    }
}
