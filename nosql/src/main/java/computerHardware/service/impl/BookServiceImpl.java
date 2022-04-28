package computerHardware.service.impl;

import computerHardware.dto.ComputerHardwareDto;
import computerHardware.dto.ComputerHardwarePageDto;
import computerHardware.mapper.ComputerHardwareMapper;
import computerHardware.model.ComputerHardware;
import computerHardware.repository.BookRepository;
import computerHardware.repository.GenreRepository;
import computerHardware.service.BookService;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final ComputerHardwareMapper computerHardwareMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ComputerHardwareDto> findAll() {
        return computerHardwareMapper.toDtos(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ComputerHardwarePageDto getPage(Pageable pageable) {
        Page<ComputerHardware> currentPage = bookRepository.findAll(pageable);
        return new ComputerHardwarePageDto(computerHardwareMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ComputerHardwareDto> getById(String bookId) {
        return computerHardwareMapper.toOptionalDto(bookRepository.findById(bookId));
    }

    @Override
    @Transactional
    public ComputerHardwareDto save(@Valid ComputerHardwareDto book) {
        ComputerHardware computerHardwareEntity = computerHardwareMapper.toEntity(book);
        genreRepository.findById(book.getGenreCode()).ifPresent(computerHardwareEntity::setGenre);
        return computerHardwareMapper.toDto(bookRepository.save(computerHardwareEntity));
    }

    @Override
    @Transactional
    public void partialSave(@Valid ComputerHardwareDto book) {
        bookRepository.findById(book.getId()).ifPresent(computerHardwareEntity -> {
            computerHardwareEntity.setIsbn(book.getIsbn());
            computerHardwareEntity.setName(book.getName());
            bookRepository.save(computerHardwareEntity);
        });
    }

    @Override
    @Transactional
    public void deleteById(String bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void delete(@Valid ComputerHardwareDto book) {
        bookRepository.delete(computerHardwareMapper.toEntity(book));
    }
}
