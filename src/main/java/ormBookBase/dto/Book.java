package ormBookBase.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@Entity
@Table(schema = "db", name = "books")
public class Book {

    @Id
    @Column(name = "isbn", nullable = false, unique = true)
    private final long longIsbn;
    @Column(name = "title")
    private final String title;
    @Column(name = "url")
    private final String url;
    @Column(name = "pages")
    private final Integer pageCount;
    @Column(name = "author_id")
    private int author_id;
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private Author author;
    @Column(name="price")
    private final Integer price;

}
