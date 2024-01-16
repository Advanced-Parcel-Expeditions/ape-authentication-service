package si.ape.authentication.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "street")
@NamedQueries(value =
        {
                @NamedQuery(name = "StreetEntity.getAll",
                        query = "SELECT s FROM StreetEntity s"),
                @NamedQuery(name = "StreetEntity.getById",
                        query = "SELECT s FROM StreetEntity s WHERE s.streetName = :streetName AND s.streetNumber = :streetNumber" +
                                " AND s.city.code = :cityCode AND s.city.name = :cityName AND s.city.country.code = :countryCode")
        })
@IdClass(StreetEntity.StreetId.class)
public class StreetEntity {

    @Id
    @Column(name = "street_name")
    private String streetName;

    @Id
    @Column(name = "street_number")
    private Integer streetNumber;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "city_code", referencedColumnName = "code"),
            @JoinColumn(name = "city_name", referencedColumnName = "name"),
            @JoinColumn(name = "country_code", referencedColumnName = "country_code")
    })
    private CityEntity city;

    public static class StreetId implements Serializable {
        private String streetName;
        private Integer streetNumber;
        private CityEntity.CityId city;

        public StreetId(String streetName, Integer streetNumber, String cityCode, String cityName, String countryCode) {
            this.streetName = streetName;
            this.streetNumber = streetNumber;
            this.city = new CityEntity.CityId(cityCode, cityName, countryCode);
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public Integer getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(Integer streetNumber) {
            this.streetNumber = streetNumber;
        }

        public CityEntity.CityId getCity() {
            return city;
        }

        public void setCity(CityEntity.CityId city) {
            this.city = city;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StreetId)) return false;
            StreetId streetId = (StreetId) o;
            return Objects.equals(getStreetName(), streetId.getStreetName()) &&
                    Objects.equals(getStreetNumber(), streetId.getStreetNumber()) &&
                    Objects.equals(getCity(), streetId.getCity());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getStreetName(), getStreetNumber(), getCity());
        }

    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

}
