package ws.api.wsapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ws.api.wsapi.dto.consumers.DtoRecovery;
import ws.api.wsapi.dto.message.MessageDTO;

public interface UserDetailService extends UserDetailsService {
    MessageDTO sendRecoveryCode(String email);
    boolean recoveryIsValid(String code, String email);
    void updatePasswordByRecoveryCode(DtoRecovery dtoRecovery);
}
