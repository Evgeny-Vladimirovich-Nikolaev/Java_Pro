package computerAccessories.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AccessoryDto {
    @NotNull
    @Size(min = 1, max = 3)
    private String code;

    @NotNull
    @Size(min = 1, max = 120)
    private String name;

    public String getCodeStr() {
        return String.format("accessory_%s", code);
    }
}
