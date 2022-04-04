package exchangeRateBrowser.controller;

import exchangeRate.aggregator.RateAggregator;
import exchangeRate.currency.CurrencyImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencyCalculator")
public class InputController {

    private final RateAggregator aggregator;

    @GetMapping("/convert")
    public BigDecimal convertToRubles (@RequestParam String code) {
        CurrencyImpl currency = null;
        try {
            currency = (CurrencyImpl) aggregator.getCurrency(code);
        } catch (IOException e) {
            System.out.println("Ошибка соединения с сервером");
            e.printStackTrace();
        }
        System.out.println(currency);
        BigDecimal result = new BigDecimal(currency.getValue() / currency.getNominal());
        return result;
    }

}
