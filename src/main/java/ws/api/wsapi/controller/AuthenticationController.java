package ws.api.wsapi.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.api.wsapi.dto.JWTDto;
import ws.api.wsapi.dto.LoginDto;
import ws.api.wsapi.service.AutenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AutenticationService autenticationService;
    public AuthenticationController(AutenticationService autenticationService) {

        this.autenticationService = autenticationService;
    }
    @GetMapping("/teste")
    public ResponseEntity<JWTDto> teste(@Valid @RequestBody LoginDto dto){
        return ResponseEntity.ok().body(autenticationService.auth(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<JWTDto> teste2(@Valid @RequestBody LoginDto dto){
        return ResponseEntity.ok().body(autenticationService.auth(dto));
    }
//    @PostMapping("/login")
//    public ResponseEntity<JWTDto> createAuthenticationToken(@Valid @RequestBody LoginDto loginDto){
//        System.out.println("ola");
//        return ResponseEntity.ok().body(autenticationService.auth(loginDto));
//    }

}
