package computerHardware.model;

import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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

    @OneToOne
    private HardwareType type;

}
