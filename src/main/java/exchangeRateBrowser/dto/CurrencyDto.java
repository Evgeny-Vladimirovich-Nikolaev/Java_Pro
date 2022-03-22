package exchangeRateBrowser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    String code;
    BigDecimal rublesRate;
    LocalDate date;

}
