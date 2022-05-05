package computerAccessories.service.impl;

import computerAccessories.dto.AccessoryDto;
import computerAccessories.dto.AccessoryPageDto;
import computerAccessories.mapper.AccessoryMapper;
import computerAccessories.model.Accessory;
import computerAccessories.repository.AccessoryRepository;
import computerAccessories.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final AccessoryMapper accessoryMapper;
    @Override
    @Transactional(readOnly = true)
    public List<AccessoryDto> findAll() {
        return accessoryMapper.toDtos(accessoryRepository.findAll());
    }

    @Override
    public AccessoryPageDto getPage(Pageable pageable) {
        Page<Accessory> currentPage = accessoryRepository.findAll(pageable);
        return new AccessoryPageDto(accessoryMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccessoryDto> getByCode(@NotEmpty String accessoryCode) {
        return accessoryMapper.toOptionalDto(accessoryRepository.findById(accessoryCode));
    }

    @Override
    @Transactional
    public AccessoryDto save(@Valid AccessoryDto accessoryDto) {
        return accessoryMapper.toDto(accessoryRepository.save(accessoryMapper.toEntity(accessoryDto)));
    }

    @Override
    @Transactional
    public void deleteByCode(@NotEmpty String accessoryCode) {
        accessoryRepository.deleteById(accessoryCode);
    }

    @Override
    @Transactional
    public void delete(@Valid Accessory accessory) {
        accessoryRepository.delete(accessory);
    }
}

