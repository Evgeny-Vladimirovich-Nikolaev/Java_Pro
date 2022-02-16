package exchangeRate.currency;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CurrencyImpl implements Currency {

    private String CharCode;
    private Integer Nominal;
    private String Name;
    private Double Value;
    private Double Previous;

    public String getChanges() {
        double changes = Value - Previous;
        if(changes <= 0.0) {
            return String.format("%.2f",changes);
        }
        return "+" + String.format("%.2f",changes);
    }

    @Override
    public String toString() {
        return String.format(
                """
                Валюта: %s (%s)
                Номинал: %s
                Курс в рублях: %s
                Изменение курса в рублях: %s
                """,
                CharCode,
                Name,
                Nominal,
                Value,
                getChanges());
    }

}
