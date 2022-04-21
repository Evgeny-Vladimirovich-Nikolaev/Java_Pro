package main.java.valuteWebRunner.controller;

import exchangeRate.aggregator.RateAggregatorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import valuteWebRunner.dto.ValuteDto;

@RestController
@RequiredArgsConstructor
public class ValuteController {

    private final RateAggregatorImpl rateAggregator;

    @GetMapping("/currency")
    public ValuteDto getValue(@RequestParam String code) {
        return new ValuteDto(code, rateAggregator.getValueByCode(code)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Информации по валюте с кодом %s не найдено в системе", code))));
    }
}
