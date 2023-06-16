package ws.api.wsapi.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import ws.api.wsapi.model.UserCredentials;
import ws.api.wsapi.service.TokenService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class TokenServiceImpl implements TokenService {

    @Value("${ws-secret.name}")
    private String segredo;

    @Value("${ws-secret.expirate}")
    private Long expirate;
    @Override
    public String getToken(Authentication auth) {
        var userCreden = (UserCredentials)auth.getPrincipal();
        var today = LocalDate.now();
        LocalDate expiresAt = today.plusDays(expirate);
        return JWT.create()
                .withIssuer("API RAS")
                .withSubject(userCreden.getId().toString())
                .withIssuedAt(Instant.from(today))
                .withExpiresAt(Instant.from(expiresAt))
                .sign(Algorithm.HMAC256(segredo));
    }
    @Override
    public Boolean isValid(String token) {
        JWTVerifier verifier = getJwtVerifier();
        try {
            verifier.verify(token);
            return true;

        }catch (Exception e){
            return false;
        }

    }

    private JWTVerifier getJwtVerifier() {
        Algorithm algorithm = Algorithm.HMAC256(segredo);
        return JWT.require(algorithm)
                // specify an specific claim validations
                .withIssuer("API RAS")
                // reusable verifier instance
                .build();
    }

    @Override
    public Long getUerId(String token) {
        DecodedJWT decodedJWT = getJwtVerifier().verify(token);
        return Long.parseLong(decodedJWT.getSubject());
    }

}
