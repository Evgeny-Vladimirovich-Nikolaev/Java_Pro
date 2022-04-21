package main.java.exchangeRateBrowser.config;

import exchangeRate.aggregator.RateAggregator;
import exchangeRate.aggregator.RateAggregatorImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("exchangeRate.aggregator")
public class AppConfig {

    @Bean
    public RateAggregator aggregator() {
        return new RateAggregatorImpl();
    }
}
