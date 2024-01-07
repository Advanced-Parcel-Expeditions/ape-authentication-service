package si.ape.authentication.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "city")
@NamedQueries(value =
        {
                @NamedQuery(name = "CityEntity.getAll",
                        query = "SELECT c FROM CityEntity c")
        })
@IdClass(CityEntity.CityId.class)
public class CityEntity {

    @Id
    private String code;

    @Id
    private String name;

    @Id
    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private CountryEntity country;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public static class CityId implements Serializable {
        private String code;
        private String name;
        private String country;

        public CityId(String code, String name, String country) {
            this.code = code;
            this.name = name;
            this.country = country;
        }

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CityId)) return false;
            CityId that = (CityId) o;
            return getCode().equals(that.getCode()) &&
                    getName().equals(that.getName()) &&
                    getCountry().equals(that.getCountry());
        }

        @Override
        public int hashCode() {
            return code.hashCode() + name.hashCode() + country.hashCode();
        }
    }

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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
