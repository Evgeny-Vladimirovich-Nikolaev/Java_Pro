package exchangeRateBrowser.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({"exchangeRate.aggregator", "exchangeRateBrowser"})
public class AppConfig {
}
