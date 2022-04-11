package computerAccessories.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "computer_accessories")
public class Accessory {

    @Id
    @NotEmpty
    private String code;


    @NotEmpty
    private String name;
}
