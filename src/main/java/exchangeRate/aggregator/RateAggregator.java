package exchangeRate.aggregator;

import exchangeRate.currency.Currency;

import java.io.IOException;

public interface RateAggregator {

    Currency getCurrency(String valuate) throws IOException;

    void clearCache();

}
