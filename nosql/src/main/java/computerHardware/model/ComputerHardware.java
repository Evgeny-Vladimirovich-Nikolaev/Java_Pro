package computerHardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "computerHardware")
public class ComputerHardware {

    @Id
    private String id;

    @NotEmpty
    private String vendor;

    @NotEmpty
    private String model;

    @DBRef
    private HardwareType hardwareType;

}
