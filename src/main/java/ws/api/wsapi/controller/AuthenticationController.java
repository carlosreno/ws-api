package ws.api.wsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.api.wsapi.dto.JWTDto;
import ws.api.wsapi.dto.LoginDto;
import ws.api.wsapi.service.AutenticationService;
import ws.api.wsapi.service.impl.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomerUserDetailsService customerUserDetailsService;
    private final AutenticationService autenticationService;
    @PostMapping("/login")
    public ResponseEntity<JWTDto> teste2(@Valid @RequestBody LoginDto dto){
        return ResponseEntity.ok().body(autenticationService.auth(dto));
    }
    @PostMapping("/send-recovery-code")
    public ResponseEntity<?> sendRecoveryCode(@RequestBody Object email){
        return ResponseEntity.ok().body(customerUserDetailsService.sendRecoveryCode(null));
    }

}
