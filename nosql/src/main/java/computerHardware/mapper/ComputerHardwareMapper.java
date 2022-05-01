package computerHardware.mapper;

import computerHardware.dto.ComputerHardwareDto;
import computerHardware.model.ComputerHardware;
import computerHardware.model.HardwareType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface ComputerHardwareMapper {

    @Mappings({
            @Mapping(target = "id", source = "computerHardware.id"),
            @Mapping(target = "vendor", source = "computerHardware.vendor"),
            @Mapping(target = "model", source = "computerHardware.model"),
            @Mapping(target = "type", source = "computerHardware.hardwareType.code")
    })
    ComputerHardwareDto toDto(ComputerHardware computerHardware);

    @Mappings({
            @Mapping(target = "id", source = "computerHardware.id"),
            @Mapping(target = "vendor", source = "computerHardware.vendor"),
            @Mapping(target = "model", source = "computerHardware.model")
    })
    ComputerHardware toEntity(ComputerHardwareDto computerHardware);
//
//    default HardwareType map(String value) {
//        //TODO
//        return null;
//    }

    default List<ComputerHardwareDto> toDtos(List<ComputerHardware> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    default Optional<ComputerHardwareDto> toOptionalDto(Optional<ComputerHardware> entity) {
        return entity.map(this::toDto);
    }

}
