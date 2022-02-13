package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @Column(columnDefinition = "id")
    private int id;
    @CsvBindByName(column = "Author")
    @Column(columnDefinition = "name")
    private String name;

    public String getName() {
        return name != "" ? name : "без автора";
    }

}
