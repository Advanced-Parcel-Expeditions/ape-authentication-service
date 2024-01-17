package si.ape.authentication.api.v1.resources.requests;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

public class RegisterEmployeeRequest {

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

    // Metadata in the `staff` table:

    @Schema(required = true)
    @JsonbProperty("firstName")
    private String firstName;

    @Schema(required = true)
    @JsonbProperty("lastName")
    private String lastName;

    @Schema(required = true)
    @JsonbProperty("branchId")
    private Integer branchId;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

}
