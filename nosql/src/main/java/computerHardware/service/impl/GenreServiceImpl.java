package computerHardware.service.impl;

import computerHardware.dto.HardwareTypeDto;
import computerHardware.model.HardwareType;
import computerHardware.service.GenreService;
import computerHardware.dto.HardwareTypePageDto;
import computerHardware.mapper.HardwareTypeMapper;
import computerHardware.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(value = "application.nosql.type", havingValue = "mongo", matchIfMissing = true)
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final HardwareTypeMapper hardwareTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HardwareTypeDto> findAll() {
        return hardwareTypeMapper.toDtos(genreRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public HardwareTypePageDto getPage(Pageable pageable) {
        Page<HardwareType> currentPage = genreRepository.findAll(pageable);
        return new HardwareTypePageDto(hardwareTypeMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HardwareTypeDto> getByCode(@NotEmpty String genreCode) {
        return hardwareTypeMapper.toOptionalDto(genreRepository.findById(genreCode));
    }

    @Override
    @Transactional
    public HardwareTypeDto save(@Valid HardwareTypeDto genre) {
        return hardwareTypeMapper.toDto(genreRepository.save(hardwareTypeMapper.toEntity(genre)));
    }

    @Override
    @Transactional
    public void deleteByCode(@NotEmpty String genreCode) {
        genreRepository.deleteById(genreCode);
    }

    @Override
    @Transactional
    public void delete(@Valid HardwareTypeDto genre) {
        genreRepository.delete(hardwareTypeMapper.toEntity(genre));
    }
}
