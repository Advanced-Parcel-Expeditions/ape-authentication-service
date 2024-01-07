package si.ape.authentication.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.jwt.auth.feature.JWTRolesAllowedDynamicFeature;
import com.kumuluz.ee.jwt.auth.filter.JWTAuthorizationFilter;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import java.util.HashSet;
import java.util.Set;

@OpenAPIDefinition(info = @Info(title = "Authentication API", version = "v1",
        contact = @Contact(email = "ls6727@student.uni-lj.si, js1471@student.uni-lj.si"),
        license = @License(name = "dev"), description = "API for managing user credentials."),
        servers = @Server(url = "http://localhost:8080/"))
@ApplicationPath("/v1")
public class AuthenticationApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(JWTAuthorizationFilter.class);
        resources.add(JWTRolesAllowedDynamicFeature.class);
        resources.add(si.ape.authentication.api.v1.resources.AuthenticationResource.class);
        return resources;
    }

}
