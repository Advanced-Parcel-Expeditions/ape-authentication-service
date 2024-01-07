package si.ape.authentication.api.v1.resources.requests;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

public class RegisterCustomerRequest {

    // Metadata in the `users` table:
    @Schema(required = true)
    @JsonbProperty("username")
    private String username;

    @Schema(required = true)
    @JsonbProperty("password")
    private String password;

    @Schema(required = true)
    @JsonbProperty("roleId")
    private Integer roleId;

    // Metadata in the `customers` table:

    @Schema(required = true)
    @JsonbProperty("name")
    private String name;

    @Schema(required = true)
    @JsonbProperty("surname")
    private String surname;

    @Schema(required = false)
    @JsonbProperty("companyName")
    private String companyName;

    @Schema(required = true)
    @JsonbProperty("telephoneNumber")
    private String telephoneNumber;

    @Schema(required = true)
    @JsonbProperty("streetName")
    private String streetName;

    @Schema(required = true)
    @JsonbProperty("streetNumber")
    private Integer streetNumber;

    @Schema(required = true)
    @JsonbProperty("cityCode")
    private String cityCode;

    @Schema(required = true)
    @JsonbProperty("cityName")
    private String cityName;

    @Schema(required = true)
    @JsonbProperty("countryCode")
    private String countryCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
