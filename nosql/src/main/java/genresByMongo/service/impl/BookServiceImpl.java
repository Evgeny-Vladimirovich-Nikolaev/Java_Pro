package genresByMongo.service.impl;

import genresByMongo.dto.BookDto;
import genresByMongo.dto.BookPageDto;
import genresByMongo.mapper.BookMapper;
import genresByMongo.model.Book;
import genresByMongo.repository.BookRepository;
import genresByMongo.repository.GenreRepository;
import genresByMongo.service.BookService;
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
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookMapper.toDtos(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BookPageDto getPage(Pageable pageable) {
        Page<Book> currentPage = bookRepository.findAll(pageable);
        return new BookPageDto(bookMapper.toDtos(currentPage.getContent()),
                currentPage.getNumber(),
                currentPage.getTotalPages(),
                currentPage.hasNext(),
                currentPage.hasPrevious());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDto> getById(String bookId) {
        return bookMapper.toOptionalDto(bookRepository.findById(bookId));
    }

    @Override
    @Transactional
    public BookDto save(@Valid BookDto book) {
        Book bookEntity = bookMapper.toEntity(book);
        genreRepository.findById(book.getGenreCode()).ifPresent(bookEntity::setGenre);
        return bookMapper.toDto(bookRepository.save(bookEntity));
    }

    @Override
    @Transactional
    public void partialSave(@Valid BookDto book) {
        bookRepository.findById(book.getId()).ifPresent(bookEntity -> {
            bookEntity.setIsbn(book.getIsbn());
            bookEntity.setName(book.getName());
            bookRepository.save(bookEntity);
        });
    }

    @Override
    @Transactional
    public void deleteById(String bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void delete(@Valid BookDto book) {
        bookRepository.delete(bookMapper.toEntity(book));
    }
}
