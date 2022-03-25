package exchangeRateBrowser.controller;

import exchangeRate.aggregator.RateAggregatorImpl;
import exchangeRate.currency.CurrencyImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencyCalculator")
public class InputController {

    //@Autowired
    //private final RateAggregatorImpl rateAggregator;
    @Value("${spring.application.name}")
    private String application;

    //private final ConverterService converterService;

    @PostMapping("/convert")
    public BigDecimal convertToRubles (@RequestParam String code, @RequestParam BigDecimal amount) {
        RateAggregatorImpl rateAggregator = new RateAggregatorImpl();
        CurrencyImpl currency = (CurrencyImpl) rateAggregator.getCurrency(code);
        System.out.println(currency);
        return new BigDecimal(currency.getValue() * amount.doubleValue() / currency.getNominal());
    }

}
