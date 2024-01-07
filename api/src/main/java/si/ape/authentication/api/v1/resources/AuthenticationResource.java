package si.ape.authentication.api.v1.resources;

import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import si.ape.authentication.api.v1.resources.requests.LoginRequest;
import si.ape.authentication.api.v1.resources.requests.RegisterCustomerRequest;
import si.ape.authentication.api.v1.resources.requests.RegisterEmployeeRequest;
import si.ape.authentication.lib.*;
import si.ape.authentication.services.beans.AuthenticationBean;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.logging.Logger;

@RequestScoped
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@DeclareRoles({"Administrator", "Warehouse manager", "Order confirmation specialist"})
public class AuthenticationResource {

    private final Logger log = Logger.getLogger(AuthenticationResource.class.getName());

    @Inject
    private AuthenticationBean authenticationBean;

    @Context
    protected UriInfo uriInfo;

    @POST
    @Path("/login")
    @Operation(summary = "Login user", description = "Login user")
    @PermitAll
    public Response login(LoginRequest loginRequest) {

        authenticationBean = CDI.current().select(AuthenticationBean.class).get();

        try {
            String compactedJwt = authenticationBean.validate(loginRequest.getUsername(), loginRequest.getPassword());

            if (compactedJwt != null) {
                return Response.ok().header("Authorization", "Bearer " + compactedJwt).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/register/customer")
    @Operation(summary = "Register customer", description = "Register customer")
    @RolesAllowed({"Administrator", "Warehouse manager", "Order confirmation specialist"})
    public Response registerCustomer(RegisterCustomerRequest registerCustomerRequest) {

        authenticationBean = CDI.current().select(AuthenticationBean.class).get();

        try {

            // Convert the request to a partial Customer DTO.
            Customer customer = getCustomer(registerCustomerRequest);

            if (authenticationBean.registerCustomer(customer) == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            log.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/register/employee")
    @Operation(summary = "Register employee", description = "Register employee")
    @RolesAllowed({"Administrator", "Warehouse manager"})
    public Response registerEmployee(RegisterEmployeeRequest registerEmployeeRequest) {

        authenticationBean = CDI.current().select(AuthenticationBean.class).get();

        try {
            // Convert the request to a partial Employee DTO.
            Employee employee = getEmployee(registerEmployeeRequest);

            if (authenticationBean.registerEmployee(employee) == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.ok().build();
        } catch (Exception e) {
            log.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    private static Street getStreet(RegisterCustomerRequest registerCustomerRequest) {
        Street street = new Street();
        street.setStreetName(registerCustomerRequest.getStreetName());
        street.setStreetNumber(registerCustomerRequest.getStreetNumber());
        City city = new City();
        city.setCode(registerCustomerRequest.getCityCode());
        city.setName(registerCustomerRequest.getCityName());
        Country country = new Country();
        country.setCode(registerCustomerRequest.getCountryCode());
        city.setCountry(country);
        street.setCity(city);
        return street;
    }

    private static Employee getEmployee(RegisterEmployeeRequest registerEmployeeRequest) {
        Employee employee = new Employee();
        employee.setName(registerEmployeeRequest.getName());
        employee.setSurname(registerEmployeeRequest.getSurname());
        Branch branch = new Branch();
        branch.setId(registerEmployeeRequest.getBranchId());
        User user = new User();
        user.setUsername(registerEmployeeRequest.getUsername());
        user.setPassword(registerEmployeeRequest.getPassword());
        Role role = new Role();
        role.setId(registerEmployeeRequest.getRoleId());
        user.setRole(role);
        employee.setUser(user);
        employee.setBranch(branch);
        return employee;
    }

    private static Customer getCustomer(RegisterCustomerRequest registerCustomerRequest) {
        Customer customer = new Customer();
        customer.setName(registerCustomerRequest.getName());
        customer.setSurname(registerCustomerRequest.getSurname());
        Role role = new Role();
        role.setId(registerCustomerRequest.getRoleId());
        User user = new User();
        user.setUsername(registerCustomerRequest.getUsername());
        user.setPassword(registerCustomerRequest.getPassword());
        user.setRole(role);
        customer.setUser(user);
        customer.setCompanyName(registerCustomerRequest.getCompanyName());
        customer.setTelephoneNumber(registerCustomerRequest.getTelephoneNumber());
        Street street = getStreet(registerCustomerRequest);
        customer.setStreet(street);
        return customer;
    }

}
