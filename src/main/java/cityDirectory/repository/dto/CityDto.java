package cityDirectory.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {
    private Integer code;
    private Integer population;
    private String enName;
    private String ruName;

    public CityDto(String enName, String ruName) {
        this.enName = enName;
        this.ruName = ruName;
    }
}
