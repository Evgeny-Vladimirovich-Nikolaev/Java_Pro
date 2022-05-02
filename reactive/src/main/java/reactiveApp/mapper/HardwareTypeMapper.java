package reactiveApp.mapper;

import reactiveApp.dto.HardwareTypeDto;
import reactiveApp.model.HardwareType;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface HardwareTypeMapper {

    HardwareTypeDto toDto(HardwareType entity);
    HardwareType toEntity(HardwareTypeDto dto);

    default List<HardwareTypeDto> toDtos(List<HardwareType> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default Optional<HardwareTypeDto> toOptionalDto(Optional<HardwareType> entity) {
        return entity.map(this::toDto);
    }
}
