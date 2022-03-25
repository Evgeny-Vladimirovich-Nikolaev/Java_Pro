package exchangeRateBrowser.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({"exchangeRateBrowser", "exchangeRate"})
public class AppConfig {
}
