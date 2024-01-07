package si.ape.authentication.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "country")
@NamedQueries(value =
        {
                @NamedQuery(name = "CountryEntity.getAll",
                        query = "SELECT c FROM CountryEntity c")
        })
public class CountryEntity {

    @Id
    private String code;

    @Column(name = "name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
