package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class BookDto {
    @CsvBindByName(column = "ISBN")
    private String isbn;
    @CsvBindByName(column = "Title")
    @Column(columnDefinition = "title")
    private String title;
    @CsvBindByName(column = "URL")
    @Column(columnDefinition = "url")
    private String url;
    @CsvBindByName(column = "PageCount")
    @Column(columnDefinition = "pages")
    private Integer pageCount;
    @CsvBindByName(column = "Author")
    private String author;
    @CsvBindByName(column = "Price")
    @Column(columnDefinition = "price")
    private BigDecimal price;

    @Id
    @Column(columnDefinition = "isbn")
    public long convertIsbnToLong() {
        //TODO
        return 0;
    }
}
