package valuteWebRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"exchangeRate", "valuteWebRunner"})
public class ValuteWebRunner {

    public static void main(String[] args) {
        SpringApplication.run(ValuteWebRunner.class, args);
    }
}
