package ws.api.wsapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.api.wsapi.dto.LoginDto;
import ws.api.wsapi.service.TokenService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.getToken(authentication);
        return ResponseEntity.ok().body(token);
    }
}
