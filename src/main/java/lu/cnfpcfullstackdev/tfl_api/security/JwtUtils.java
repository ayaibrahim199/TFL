package lu.cnfpcfullstackdev.tfl_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class JwtUtils {

    private final String secret;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtUtils(@Value("${app.jwt.secret:}") String secretFromProperty) {
        String env = System.getenv("JWT_SECRET");
        if (secretFromProperty != null && !secretFromProperty.isBlank()) {
            this.secret = secretFromProperty;
        } else if (env != null && !env.isBlank()) {
            this.secret = env;
        } else {
            // Development fallback - change before production
            this.secret = "dev-secret-change-me";
        }
        this.algorithm = Algorithm.HMAC256(this.secret);
        this.verifier = JWT.require(this.algorithm).build();
    }

    public boolean validate(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    public String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public List<String> getRoles(String token) {
        DecodedJWT jwt = JWT.decode(token);
        // Try 'roles' claim: could be array or comma-separated string
        try {
            Object claim = jwt.getClaim("roles").as(Object.class);
            if (claim == null) return Collections.emptyList();
            String asString = jwt.getClaim("roles").asString();
            if (asString != null) {
                return Arrays.asList(asString.split(","));
            }
            String[] arr = jwt.getClaim("roles").asArray(String.class);
            if (arr != null) return Arrays.asList(arr);
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }

}
