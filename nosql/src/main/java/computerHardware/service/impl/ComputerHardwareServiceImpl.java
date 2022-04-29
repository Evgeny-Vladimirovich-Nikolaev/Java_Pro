package computerHardware.service.impl;

import computerHardware.dto.ComputerHardwareDto;
import computerHardware.dto.ComputerHardwarePageDto;
import computerHardware.mapper.ComputerHardwareMapper;
import computerHardware.model.ComputerHardware;
import computerHardware.repository.ComputerHardwareRepository;
import computerHardware.repository.HardwareTypeRepository;
import computerHardware.service.ComputerHardwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "application.nosql.type", havingValue = "mongo", matchIfMissing = true)
public class ComputerHardwareServiceImpl implements ComputerHardwareService {

    private final ComputerHardwareRepository computerHardwareRepository;
    private final HardwareTypeRepository hardwareTypeRepository;
    private final ComputerHardwareMapper computerHardwareMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ComputerHardwareDto> findAll() {
        return computerHardwareMapper.toDtos(computerHardwareRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ComputerHardwarePageDto getPage(Pageable pageable) {
        Page<ComputerHardware> currentPage = computerHardwareRepository.findAll(pageable);
        return new ComputerHardwarePageDto(computerHardwareMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ComputerHardwareDto> getById(String bookId) {
        return computerHardwareMapper.toOptionalDto(computerHardwareRepository.findById(bookId));
    }

    @Override
    @Transactional
    public ComputerHardwareDto save(@Valid ComputerHardwareDto book) {
        ComputerHardware computerHardwareEntity = computerHardwareMapper.toEntity(book);
        hardwareTypeRepository.findById(book.getGenreCode()).ifPresent(computerHardwareEntity::setGenre);
        return computerHardwareMapper.toDto(computerHardwareRepository.save(computerHardwareEntity));
    }

    @Override
    @Transactional
    public void partialSave(@Valid ComputerHardwareDto book) {
        computerHardwareRepository.findById(book.getId()).ifPresent(computerHardwareEntity -> {
            computerHardwareEntity.setIsbn(book.getIsbn());
            computerHardwareEntity.setName(book.getName());
            computerHardwareRepository.save(computerHardwareEntity);
        });
    }

    @Override
    @Transactional
    public void deleteById(String bookId) {
        computerHardwareRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void delete(@Valid ComputerHardwareDto book) {
        computerHardwareRepository.delete(computerHardwareMapper.toEntity(book));
    }
}
