package ws.api.wsapi.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ws.api.wsapi.model.UserCredentials;
import ws.api.wsapi.service.TokenService;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${ws-secret.name}")
    private String segredo;

    @Value("${ws-secret.expiration}")
    private Long expiration;
    @Override
    public String getToken(Authentication auth) {
        var userCredence = (UserCredentials) auth.getPrincipal();
        LocalDate expiresAt = LocalDate.now().plusDays(expiration);
        Date convertExpiresAt = Date.from(expiresAt.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(userCredence);
        return JWT.create()
                .withIssuer("API RAS")
                .withSubject(userCredence.getId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(convertExpiresAt)
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
    public Long getUserId(String token) {
        DecodedJWT decodedJWT = getJwtVerifier().verify(token);
        return Long.parseLong(decodedJWT.getSubject());
    }


}
