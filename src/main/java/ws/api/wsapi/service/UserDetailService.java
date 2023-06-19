package ws.api.wsapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {
    Object sendRecoveryCode(String email);
}
