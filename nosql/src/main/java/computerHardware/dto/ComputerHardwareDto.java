package computerHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerHardwareDto {

    private String id;
    @NotEmpty
    @Size(min = 1, max = 120)
    private String vendor;
    @NotEmpty
    @Size(min = 1, max = 120)
    private String model;

    private String type;
}
