package exchangeRateBrowser.controller;

import exchangeRateBrowser.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currencyCalculator")
public class InputController {

    @Value("${spring.application.name}")
    private String application;

    //private final ConverterService converterService;

    @PostMapping("/convert")
    public BigDecimal convertToRubles (@RequestParam String code, @RequestParam BigDecimal amount) {
        return new BigDecimal(1000000.5);
    }

}
