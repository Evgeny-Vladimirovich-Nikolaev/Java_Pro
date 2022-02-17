package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "books")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "db", name = "authors")
public class Author {

    @Id
    @Column()
    private int id;
    @CsvBindByName(column = "Author")
    @Column(name = "author", unique = true)
    private String name;
    @OneToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public String getName() {
        return !Objects.equals(name, "") ? name : "без автора";
    }

}
