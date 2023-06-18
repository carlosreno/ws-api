package ws.api.wsapi.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.model.UserCredentials;
import ws.api.wsapi.repositories.UserDetailsRepository;
import ws.api.wsapi.service.TokenService;

import java.io.IOException;
import java.util.Objects;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    private final UserDetailsRepository userDetailsRepository;

    public JwtAuthenticationFilter(TokenService tokenService, UserDetailsRepository userDetailsRepository) {
        this.tokenService = tokenService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = getBearerToken(request);
        System.out.println(token);
        if (Boolean.TRUE.equals(tokenService.isValid(token))) {
            System.out.println("é valido rapax");
            authByToken(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authByToken(String token) {

        //recuperar id do usuario
        Long userId = tokenService.getUserId(token);
        var userOpt = userDetailsRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        UserCredentials userCredentials = userOpt.get();
        //autenticar no spring

        UsernamePasswordAuthenticationToken userAuth
                = new UsernamePasswordAuthenticationToken(userCredentials, null, userCredentials.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userAuth);
    }

    private String getBearerToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7);
    }

}
