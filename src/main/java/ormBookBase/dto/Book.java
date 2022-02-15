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
    @Column
    private final String title;
    @Column
    private final String url;
    @Column(name = "pages")
    private final Integer pageCount;
    @Column
    private int authors_id;
    @ManyToOne
    @JoinColumn(name = "authors_id")
    private Author authors;
    @Column
    private final Integer price;

}
