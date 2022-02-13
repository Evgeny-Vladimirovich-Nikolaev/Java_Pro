package ormBookBase.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "authors")
public class AuthorDto {

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
