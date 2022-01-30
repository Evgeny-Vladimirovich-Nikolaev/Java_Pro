import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@Data

public class main {
    @JsonProperty("description")
    private String description;
    @JsonProperty("temp")
    private double temperature;
    @JsonProperty("feels_like")
    private double feelingTemperature;
    @JsonProperty("temp_min")
    private double minTemperature;
    @JsonProperty("temp_max")
    private double maxTemperature;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("humidity")
    private int humidity;


}
