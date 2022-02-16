package exchangeRate.aggregator;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import exchangeRate.currency.Currency;
import exchangeRate.currency.CurrencyImpl;
import org.springframework.stereotype.Service;
import utils.ResourcesReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Currency> rate = new HashMap<>();

    @Override
    public void clearCache() {
        rate.clear();
    }

    @Override
    public Currency getCurrency(String code) throws IOException {
        String c = code.toUpperCase();
        if (rate.containsKey(c)) {
            System.out.println("Результат будет получен из кэша");
            return rate.get(c);
        } else {
            return parseCurrency(c);
        }
    }

    public Currency parseCurrency(String code) {
        DocumentContext source = null;
        try {
            source = JsonPath.parse(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Currency newItem = new CurrencyImpl(
                (source.read(String.format("$['Valute']['%s']['CharCode']", code))),
                (source.read(String.format("$['Valute']['%s']['Nominal']", code))),
                (source.read(String.format("$['Valute']['%s']['Name']", code))),
                (source.read(String.format("$['Valute']['%s']['Value']", code))),
                (source.read(String.format("$['Valute']['%s']['Previous']", code)))
        );
        rate.put(code, newItem);
        return newItem;
    }
}
