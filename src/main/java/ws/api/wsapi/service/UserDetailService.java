package ws.api.wsapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ws.api.wsapi.dto.DtoRecovery;
import ws.api.wsapi.dto.message.MessageDTO;
import ws.api.wsapi.model.jpa.UserCredentials;

public interface UserDetailService extends UserDetailsService {
    MessageDTO sendRecoveryCode(String email);
    boolean recoveryIsValid(String code, String email);
    MessageDTO updatePasswordByRecoveryCode(DtoRecovery dtoRecovery);
}
