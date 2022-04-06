package exchangeRateBrowser;

import bankAccountService.service.BankingOperations;
import bankAccountService.service.impl.BankingOperationsImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeRateBrowser {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateBrowser.class, args);

    }

}
