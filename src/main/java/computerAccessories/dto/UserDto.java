package computerAccessories.dto;

import lombok.Data;
import computerAccessories.model.type.Status;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDto {

    @NotNull
    private long id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private Status status;
    private List<RoleDto> role;

}
