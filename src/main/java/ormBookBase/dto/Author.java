package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(columnDefinition = "id")
    private int id;
    @CsvBindByName(column = "Author")
    @Column(columnDefinition = "name")
    private String name;

    public String getName() {
        return !Objects.equals(name, "") ? name : "без автора";
    }

}
