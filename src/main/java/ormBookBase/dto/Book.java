package main.java.ormBookBase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ormBookBase.dto.Author;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(schema = "db", name = "books")
public class Book {

    @Id
    @Column(name = "isbn", nullable = false, unique = true)
    private long longIsbn;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "pages")
    private Integer pageCount;
    @Column(name = "author_id",updatable = false, insertable = false)
    private int author_id;
    @Column(name="price")
    private Integer price;
    @Transient
    private String authorName;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
