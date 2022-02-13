package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

@Data
@Entity(name = "books")
@Table(name = "books")
@NoArgsConstructor
public class Books {
    @Id
    @Column(columnDefinition = "isbn")
    private long longIsbn;
    @CsvBindByName(column = "ISBN")
    private String stringIsbn;
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

    public long getLongIsbn() {
        return Long.parseLong(stringIsbn.replaceAll("[^0-9.]", ""));
    }
}
