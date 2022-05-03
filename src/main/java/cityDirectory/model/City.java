package main.java.cityDirectory.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "code")
    private Integer code;
    @Column(name = "name_ru")
    private String ruName;
    @Column(name = "name_en")
    private String enName;
    @Nullable
    @Column(name = "population")
    private Integer population;

    public City(Integer code, String ruName, String enName) {
        this.code = code;
        this.ruName = ruName;
        this.enName = enName;
    }
}
