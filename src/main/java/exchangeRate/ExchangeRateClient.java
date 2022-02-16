package exchangeRate;

import exchangeRate.aggregator.RateAggregator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class ExchangeRateClient {

    private static final AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext("exchangeRate");
    private static final RateAggregator aggregator = appContext.getBean(RateAggregator.class);

    public static void main(String[] args) throws IOException {
        System.out.println(aggregator.getCurrency("amd"));
        System.out.println(aggregator.getCurrency("AUD"));
        System.out.println(aggregator.getCurrency("AMD"));
    }

}
