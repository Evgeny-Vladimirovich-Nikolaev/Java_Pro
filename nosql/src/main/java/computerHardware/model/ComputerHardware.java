package computerHardware.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Document(collection = "computerHardware")
public class ComputerHardware {

    @Id
    private String id;

    @NotEmpty
    private String vendor;

    @NotEmpty
    private String model;

    //@OneToOne
    @DBRef
    private HardwareType hardwareType;

}
