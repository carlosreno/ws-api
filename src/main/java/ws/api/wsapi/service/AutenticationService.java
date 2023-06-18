package ws.api.wsapi.service;

import ws.api.wsapi.dto.JWTDto;
import ws.api.wsapi.dto.LoginDto;

public interface AutenticationService {
    JWTDto auth(LoginDto dto);
}