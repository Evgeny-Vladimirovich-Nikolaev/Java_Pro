package computerAccessories.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import computerAccessories.dto.UserDto;
import computerAccessories.model.User;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "role", source = "user.role")
    UserDto toDto(User user);

    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "login", source = "userDto.login")
    @Mapping(target = "password", source = "userDto.password")
    @Mapping(target = "status", source = "userDto.status")
    @Mapping(target = "role", source = "userDto.role")
    User toEntity(UserDto userDto);

}
