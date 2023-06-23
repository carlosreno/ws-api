package ws.api.wsapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.api.wsapi.dto.JWTDto;
import ws.api.wsapi.dto.LoginDto;
import ws.api.wsapi.dto.DtoRecovery;
import ws.api.wsapi.dto.message.MessageDTO;
import ws.api.wsapi.model.redis.UserRecoveryCode;
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
    @PostMapping("/recovery-code/send")
    public ResponseEntity<MessageDTO> sendRecoveryCode(@Valid @RequestBody UserRecoveryCode recoveryCode){
        return ResponseEntity.ok().body(customerUserDetailsService.sendRecoveryCode(recoveryCode.getEmail()));
    }
    @PostMapping("/recovery-code")
    public ResponseEntity<?> recoveryAcess(@Valid @Email @RequestParam("email") String email,
                                                    @Valid @NotBlank @RequestParam("recoveryCode") String code){
        return ResponseEntity.ok().body(customerUserDetailsService.recoveryIsValid(code,email));
    }
    @PostMapping("/recovery-code/password")
    public ResponseEntity<MessageDTO> recoveryAcess(@Valid @RequestBody DtoRecovery dtoRecovery){
        return ResponseEntity.ok().body(customerUserDetailsService.updatePasswordByRecoveryCode(dtoRecovery));
    }

}
