package anotherBookBase;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "anotherBook")
@NoArgsConstructor
@AllArgsConstructor
public class AnotherBook {

    @Id
    private Integer id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

}

