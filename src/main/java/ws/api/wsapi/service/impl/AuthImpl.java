package ws.api.wsapi.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ws.api.wsapi.dto.JWTDto;
import ws.api.wsapi.dto.LoginDto;
import ws.api.wsapi.service.AutenticationService;
import ws.api.wsapi.service.TokenService;

@Service
public class AuthImpl implements AutenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @Override
    public JWTDto auth(LoginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUserName(),dto.getPassWord());
        Authentication userAuth= authenticationManager.authenticate(authenticationToken);
        try {
            String token = tokenService.getToken(userAuth);
            return JWTDto.builder()
                    .type("Bearer")
                    .token(token)
                    .build();
        } catch (Exception e) {
            throw new BadCredentialsException("user ou senha invalida");
        }

    }
}