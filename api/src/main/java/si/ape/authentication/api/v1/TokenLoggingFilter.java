package si.ape.authentication.api.v1;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.security.Key;
import java.security.KeyPair;
import java.util.logging.Logger;

@Provider
@Priority(10000)
public class TokenLoggingFilter implements ContainerRequestFilter {

    private static final Logger log = Logger.getLogger(TokenLoggingFilter.class.getName());
    //private static final Key key = Keys.hmacShaKeyFor("apeapeapeapeapeapeapeape123123123123123".getBytes());

    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Extract JWT token from Authorization header
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extract the token excluding "Bearer "

            System.out.println("Token: " + token);

            // Decode and log the claims
            try {
                Claims claims = Jwts.parserBuilder().setSigningKey(keyPair.getPublic()).build().parseClaimsJws(token).getBody();
                log.info("Received token with claims: " + claims.toString());
            } catch (Exception e) {
                log.warning("Failed to parse the incoming JWT token: " + e.getMessage());
            }
        }
    }

}
