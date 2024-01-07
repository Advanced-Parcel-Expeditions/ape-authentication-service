package si.ape.authentication.api.v1.resources.requests;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

public class LoginRequest {
    @Schema(required = true)
    @JsonbProperty("username")
    private String username;
    @Schema(required = true)
    @JsonbProperty("password")
    private String password;

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

}
