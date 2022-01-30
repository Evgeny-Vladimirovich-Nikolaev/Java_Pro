import com.fasterxml.jackson.annotation.JsonProperty;

public class Current {

private int humidity;
private int pressure;


    public class Temperature {

        @JsonProperty("employee")
        private double value;
        private double feeling;
        private double max;
        private double min;
        private String unit;

    }
}
