package si.ape.authentication.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@NamedQueries(value =
        {
                @NamedQuery(name = "CustomerEntity.getAll",
                        query = "SELECT c FROM CustomerEntity c"),
                @NamedQuery(name = "CustomerEntity.getByUserId",
                        query = "SELECT c FROM CustomerEntity c WHERE c.user.id = :userId")
        })
public class CustomerEntity {

    @Id
    private Integer id;

    private String name;

    private String surname;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "tel_num")
    private String telephoneNumber;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "street_name", referencedColumnName = "street_name"),
            @JoinColumn(name = "street_number", referencedColumnName = "street_number"),
            @JoinColumn(name = "city_code", referencedColumnName = "code"),
            @JoinColumn(name = "city_name", referencedColumnName = "name"),
            @JoinColumn(name = "city_country", referencedColumnName = "country_code")
    })
    private StreetEntity street;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public StreetEntity getStreet() {
        return street;
    }

    public void setStreet(StreetEntity street) {
        this.street = street;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
