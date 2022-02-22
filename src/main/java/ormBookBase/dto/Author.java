package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "db", name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true)
    private int id;
    @CsvBindByName(column = "Author")
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public String getName() {
        return !Objects.equals(name, "") ? name : "без автора";
    }

    @Override
    public String toString() {
        return "[name=" + name + ", id=" + id +"]";
    }

}
