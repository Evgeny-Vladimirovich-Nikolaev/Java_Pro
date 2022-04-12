package computerAccessories.mapper.impl;

import computerAccessories.dto.AccessoryDto;
import computerAccessories.mapper.AccessoryMapper;
import computerAccessories.model.Accessory;
import org.mapstruct.Mapper;


public class AccessoryMapperImpl implements AccessoryMapper {
    @Override
    public AccessoryDto toDto(Accessory entity) {
        AccessoryDto accessoryDto = new AccessoryDto();
        accessoryDto.setCode(entity.getCode());
        accessoryDto.setName(entity.getName());
        return accessoryDto;
    }

    @Override
    public Accessory toEntity(AccessoryDto dto) {
        return new Accessory(dto.getCodeStr(), dto.getName());
    }
}
