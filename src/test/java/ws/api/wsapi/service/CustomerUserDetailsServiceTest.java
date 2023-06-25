package ws.api.wsapi.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ws.api.wsapi.integration.MailIntegration;
import ws.api.wsapi.model.redis.UserRecoveryCode;
import ws.api.wsapi.repositories.jpa.UserDetailsRepository;
import ws.api.wsapi.repositories.redis.UserRecoveryCodeRepository;
import ws.api.wsapi.service.impl.CustomerUserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerUserDetailsServiceTest {
    @Mock
    private  UserDetailsRepository userDetailsRepository;
    @Mock
    private UserRecoveryCodeRepository recoveryCodeRepository;
    @Mock
    private MailIntegration mailIntegration;
    @InjectMocks
    private CustomerUserDetailsService customerUserDetailsService;
    private UserRecoveryCode userRecoveryCode;
    private String email;
    @BeforeEach
    public void loadRecoveryCode(){
        email = "davisarse@gmail.com";
        userRecoveryCode = new UserRecoveryCode(){{
            setEmail(email);
            setCode("1234");
        }};
    }
    @Test
    void sendRecoveryCode() {

        Mockito.when(recoveryCodeRepository.findUserRecoveryCodesByEmail(email)).thenReturn(Optional.ofNullable(userRecoveryCode));
    }

    @Test
    void loadUserByUsername() {
    }
}