package main.java.exchangeRate.currency;

import java.math.BigDecimal;

public interface Currency {
    Double getValue();
    Integer getNominal();
    String getChanges();
}
