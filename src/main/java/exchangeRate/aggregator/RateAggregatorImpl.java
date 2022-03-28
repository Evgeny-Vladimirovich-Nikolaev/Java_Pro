package exchangeRate.aggregator;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import exchangeRate.RateMessage;
import exchangeRate.currency.Currency;
import exchangeRate.currency.CurrencyImpl;
import org.springframework.stereotype.Service;
import utils.ResourcesReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RateAggregatorImpl implements RateAggregator {

    private static URL url;
    static {
        try {
            url = new URL(ResourcesReader.readText("/exchangeRate/url.txt"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private final Map<String, Currency> rate = new HashMap<>();

    @Override
    public void clearCache() {
        rate.clear();
    }

    @Override
    public Currency getCurrency(String code) {
        String c = code.toUpperCase();
        if (rate.containsKey(c)) {
            System.out.println("Результат будет получен из кэша");
            return rate.get(c);
        } else {
            return parseCurrency(c);
        }
    }

    public Optional<BigDecimal> getValueByCode(String code) {
        Currency currency = getCurrency(code);
        return Optional.ofNullable(new BigDecimal(currency.getValue() / currency.getNominal()));
    }

    public Currency parseCurrency(String code) {
        DocumentContext source;
        Currency newItem = null;
        try {
            source = JsonPath.parse(url);
            newItem = new CurrencyImpl(
                    (source.read(String.format("$['Valute']['%s']['CharCode']", code))),
                    (source.read(String.format("$['Valute']['%s']['Nominal']", code))),
                    (source.read(String.format("$['Valute']['%s']['Name']", code))),
                    (source.read(String.format("$['Valute']['%s']['Value']", code))),
                    (source.read(String.format("$['Valute']['%s']['Previous']", code)))
            );
            rate.put(code, newItem);
        } catch (PathNotFoundException e) {
            System.out.println(RateMessage.ERROR_CODE.getMsg());
        } catch (IOException e) {
            System.out.println(RateMessage.ERROR_IO.getMsg());
        }
        return newItem;
    }
}
