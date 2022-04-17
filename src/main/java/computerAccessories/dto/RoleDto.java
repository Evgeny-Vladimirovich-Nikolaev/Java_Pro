package computerAccessories.dto;

import lombok.Data;
import java.util.List;

@Data
public class RoleDto {

    private long id;
    private String name;
    private List<UserDto> user;
}
