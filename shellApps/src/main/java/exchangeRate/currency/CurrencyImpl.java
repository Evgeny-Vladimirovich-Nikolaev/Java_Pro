package main.java.exchangeRate.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrencyImpl implements Currency {

    private String charCode;
    private Integer nominal;
    private String name;
    private Double value;
    private Double previous;

    public String getChanges() {
        double changes = value - previous;
        if(changes <= 0.0) {
            return String.format("%.2f",changes);
        }
        return "+" + String.format("%.2f",changes);
    }

    @Override
    public String toString() {
        return String.format(
                """
                Код валюты: %s
                Курс в рублях: %s за %s %s
                Изменение курса в рублях: %s
                """,
                charCode,
                String.format("%.2f",value),
                nominal,
                name,
                getChanges());
    }

}
