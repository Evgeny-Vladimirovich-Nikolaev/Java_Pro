package ormBookBase.csvModels;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NoArgsConstructor;
import ormBookBase.dto.Author;
import ormBookBase.dto.Book;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookModel {
    @CsvBindByName(column = "ISBN")
    private String isbn;
    @CsvBindByName(column = "Title")
    private String title;
    @CsvBindByName(column = "URL")
    private String url;
    @CsvBindByName(column = "PageCount")
    private Integer pageCount;
    @CsvBindByName(column = "Author")
    private String author;
    @CsvBindByName(column = "Price")
    private Integer price;

    public long getLongIsbn() {
        return Long.parseLong(isbn.replaceAll("[^0-9.]", ""));
    }

    public Book getBook() {
        Book book =  new Book();
        book.setLongIsbn(getLongIsbn());
        book.setTitle(title);
        book.setUrl(url);
        book.setPageCount(pageCount);
        book.setPrice(price);
        return book;
    }
}
