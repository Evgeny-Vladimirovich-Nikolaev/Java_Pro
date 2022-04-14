package computerAccessories.mapper;

import computerAccessories.dto.AccessoryDto;
import computerAccessories.model.Accessory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AccessoryMapper {

    @Mapping(target = "code", source = "entity.code")
    @Mapping(target = "name", source = "entity.name")
    AccessoryDto toDto(Accessory entity);

    @Mapping(target = "code", source = "dto.code")
    @Mapping(target = "name", source = "dto.name")
    Accessory toEntity(AccessoryDto dto);

    default List<AccessoryDto> toDtos(List<Accessory> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default Optional<AccessoryDto> toOptionalDto(Optional<Accessory> entity) {
        return entity.map(this::toDto);
    }
}
