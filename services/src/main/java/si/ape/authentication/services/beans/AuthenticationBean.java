package si.ape.authentication.services.beans;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import si.ape.authentication.lib.Customer;
import si.ape.authentication.lib.Employee;
import si.ape.authentication.models.entities.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;


@ApplicationScoped
public class AuthenticationBean {

    private final Logger log = Logger.getLogger(AuthenticationBean.class.getName());

    @Inject
    private EntityManager em;

    public String validate(String username, String password) {
        UserEntity userEntity = em.createNamedQuery("UserEntity.getByUsername", UserEntity.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (userEntity != null && validatePassword(password, userEntity.getPassword())) {
            // Generate microprofile compliant JWT token.
            KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

            String role = userEntity.getRole().getRoleName();

            String privateKeyString = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCpJVCU/SKhL5Is\n" +
                    "Pz5zcUad3gFmo+ZBFOCtc40IPc5+tBEtpvgt5H3u5jtgjz7yPsuySUX8xEqUB4h8\n" +
                    "8QnNmDLpZ8UUCIpvXEz6nbfBfBrljRdZWTweRsfbCR3s/ZQAFXS5yPP++SxUpXwa\n" +
                    "MkiPMRzF4j8au5Waqc31ocq5CtAGSyUIzRtOt+YDANwRiN9HNwOBugE0Qllhy7hT\n" +
                    "/WjbBXv2aqk/xWIME8g3jvq+F7a3geZN19Gl6Oh0L3iL7X/Llu/wLdwr8JpuEt16\n" +
                    "JAm909zxx87G5GU6zi79/RQ3SqvWGymgIjzK2lCeU5bFz0Dwrx8Zv/FCCcIv4EpA\n" +
                    "CawgBINBAgMBAAECggEAFoVnY08JC1kgJ1AMaqaMdnyWxynPgJ7dA3s9Lfm8wtYK\n" +
                    "638t0D56HhL9mO+5u9tsl8J11R/1phwSqkAVxBAdx0IP1YkFlao+faf+TX7Ovf25\n" +
                    "PCZLgOR/u8RM/b8Ns60nq4z8H9jhdCvMiFhlnNs3L4neSRrs4nj1eV0fdt/frtaf\n" +
                    "GjoWu1FCWuL5rndcYb1pSRI3DJctWKIMvOfFKv/YePc2tyA0hdpSC803+TS3bjYX\n" +
                    "4m2SZMPA7ObNJx6H0HTGjqv+f1yJ3uAdf17LxSFHTpJ809vHFzHVHMe9DpxNBR82\n" +
                    "QeqU3C9/D02iR0wI67d76Mv7fGsUCeiYm6eIGpJs2QKBgQDWCmJVA2OmOQeknjkA\n" +
                    "gcyEPYxcMxaoHWIkJXMVIjvfMW8BjqKJBJjbqwAQC7UolPCs5/xo+g3hLUvkzFYA\n" +
                    "cBcS7rNRIeJjztifLvc+IGFDxmTK/n3G5Wl+RkZmlJrZuhOisI7RcyKI8VJl5gRV\n" +
                    "NgXWCzDIYYRMwozSX3EvlARixwKBgQDKTeMW32z2B6kqu/riM2EjP9naURRmdj7S\n" +
                    "s5nQrW75gt2BXfdhNnRL844LErWe45BmYvBy/P/EqUubUeDfcEDQ/lpLzmk9vSb8\n" +
                    "prMg0EnOZGzUxhhCXGhCrwEzWJlM3/9yt+1YfEMY292lYDk5Thmozn0yXuQlqJHE\n" +
                    "JdQMiGfhtwKBgQCv/6/lmbjZ2LEpMpVKjnCeGIIO2k96A1dMw6KCsTisCVju3dtW\n" +
                    "cXpDvrN5fjcDXJ0egE9z/pFlJDrwsvjQuVqvzamTQBqI2XAUTKku46827K66oFLK\n" +
                    "Np/tHloLRYyjFbfJogZ9wX+XPV/0vBO6AYcA/wio7E6+U4c6ARLv/XUxxwKBgF82\n" +
                    "60OvonvlPSRk1v6xie1qNuF+uahDzOukJifGCO4cPp6NmEnDtFau6dKiWqLOnf7i\n" +
                    "YCLumn5346LVlY30Kj1zCRKWsoehSAEvgC1YevRN5b77AR3ymguQjHMbJwO8hWD7\n" +
                    "2hSEX5wKlzYnpKco8AC4NsqHSL/Ep952o2fGp4XfAoGBALoXhnf4aGJ4jZpzRMMT\n" +
                    "VXj4nQ5KnIYekbj+IdDElU62CCr/FrQW92usEFWiV11U08bcndybvaj0Mg/KdhOX\n" +
                    "fHQBzJb++RloHQaLgRo7HAz/wwZ/IZO598opPH7CIOCA7JNqjLOGOj+IeNEIlI4W\n" +
                    "q3gqL5zP4xfE0SgRNEue42wH";
            privateKeyString = privateKeyString.replace("\n", "").replace("\r", "");

            // Generate private key from string
            byte[] privateKeyBytes = java.util.Base64.getDecoder().decode(privateKeyString);
            // Convert the bytes into a Key instance
            PrivateKey key = null;
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                key = keyFactory.generatePrivate(keySpec);
            } catch (Exception e) {
                log.severe("Error while generating private key: " + e.getMessage());
                return null;
            }

            String jwtToken = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(username)
                    .setIssuer("ape-authentication-service")
                    .claim("upn", username)
                    .claim("nbf", new Date())
                    .claim("groups", Arrays.asList(role))
                    .claim("roles", Arrays.asList(role))
                    .claim("typ", "https://example.com/register")
                    .setId("1234567890")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                    .signWith(key)
                    .compact();

            System.out.println("JWT token: " + jwtToken);

            return jwtToken;
        } else {
            return null;
        }
    }

    private boolean validatePassword(String password, String hashedPassword) {
        String passwordHash = getPasswordHash(password);
        if (passwordHash != null) {
            return passwordHash.equals(hashedPassword);
        }
        return false;
    }

    private String getPasswordHash(String password) {
        try {
            String statement = "SELECT PASSWORD(?)";
            String passwordHash = em.createNativeQuery(statement)
                    .setParameter(1, password)
                    .getSingleResult()
                    .toString();
            return passwordHash;
        } catch (Exception e) {
            log.severe("Error while hashing password: " + e.getMessage());
            return null;
        }
    }

    public CustomerEntity registerCustomer(Customer customerDto) {
        try {
            RoleEntity roleEntity = em.createNamedQuery("RoleEntity.getById", RoleEntity.class)
                    .setParameter("roleId", 8)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(customerDto.getUser().getUsername());
            userEntity.setPassword(getPasswordHash(customerDto.getUser().getPassword()));
            userEntity.setRole(roleEntity);
            em.persist(userEntity);

            StreetEntity streetEntity = em.createNamedQuery("StreetEntity.getById", StreetEntity.class)
                    .setParameter("streetNumber", customerDto.getStreet().getStreetNumber())
                    .setParameter("streetName", customerDto.getStreet().getStreetName())
                    .setParameter("cityCode", customerDto.getStreet().getCity().getCode())
                    .setParameter("cityName", customerDto.getStreet().getCity().getName())
                    .setParameter("countryCode", customerDto.getStreet().getCity().getCountry().getCode())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setName(customerDto.getName());
            customerEntity.setSurname(customerDto.getSurname());
            customerEntity.setCompanyName(customerDto.getCompanyName());
            customerEntity.setTelephoneNumber(customerDto.getTelephoneNumber());
            customerEntity.setUser(userEntity);
            customerEntity.setStreet(streetEntity);
            em.persist(customerEntity);

            commitTx();
            return customerEntity;
        } catch (Exception e) {
            log.severe("Error while registering customer: " + e.getMessage());
            rollbackTx();
            return null;
        }
    }

    public EmployeeEntity registerEmployee(Employee employeeDto) {
        try {
            beginTx();

            RoleEntity roleEntity = em.createNamedQuery("RoleEntity.getById", RoleEntity.class)
                    .setParameter("roleId", employeeDto.getUser().getRole().getId())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (roleEntity == null) {
                log.severe("Role with ID " + employeeDto.getUser().getRole().getId() + " does not exist.");
                rollbackTx();
                return null;
            }

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(employeeDto.getUser().getUsername());
            userEntity.setPassword(getPasswordHash(employeeDto.getUser().getPassword()));
            userEntity.setRole(roleEntity);
            em.persist(userEntity);

            BranchEntity branchEntity = em.createNamedQuery("BranchEntity.getById", BranchEntity.class)
                    .setParameter("branchId", employeeDto.getBranch().getId())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (branchEntity == null) {
                log.severe("Branch with ID " + employeeDto.getBranch().getId() + " does not exist.");
                rollbackTx();
                return null;
            }

            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setName(employeeDto.getName());
            employeeEntity.setSurname(employeeDto.getSurname());
            employeeEntity.setUser(userEntity);
            employeeEntity.setBranch(branchEntity);
            em.persist(employeeEntity);

            commitTx();
            return employeeEntity;
        } catch (Exception e) {
            log.severe("Error while registering employee: " + e.getMessage());
            rollbackTx();
            return null;
        }
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}
