package computerAccessories.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import computerAccessories.dto.RoleDto;
import computerAccessories.model.Role;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RoleMapper {


    @Mapping(target = "id", source = "role.id")
    @Mapping(target = "name", source = "role.name")
    @Mapping(target = "user", source = "role.user", ignore = true)
    RoleDto toDto(Role role);


    default List<RoleDto> toDtos(List<Role> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
