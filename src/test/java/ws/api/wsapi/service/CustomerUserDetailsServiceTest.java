package ws.api.wsapi.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import ws.api.wsapi.dto.message.MessageDTO;
import ws.api.wsapi.exception.BadRequestException;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.integration.MailIntegration;
import ws.api.wsapi.model.jpa.UserCredentials;
import ws.api.wsapi.model.redis.UserRecoveryCode;
import ws.api.wsapi.repositories.jpa.UserDetailsRepository;
import ws.api.wsapi.repositories.redis.UserRecoveryCodeRepository;
import ws.api.wsapi.service.impl.CustomerUserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    private UserCredentials userCredentials;
    @BeforeEach
    public void loadRecoveryCode(){
        email = "davisarse@gmail.com";
        userRecoveryCode = new UserRecoveryCode(){{
            setEmail(email);
            setCode("1234");
            setId("teste_id_1");
        }};
        userCredentials = new UserCredentials(){{
            setId(1L);
            setUserName("davizao - teste");
        }};
    }
    @Test
    void testSendRecoveryCodeIfUserRecoveryCodeIsEmptyAndUserNameNotFound() {
        when(recoveryCodeRepository.findUserRecoveryCodesByEmail(email)).thenReturn(Optional.empty());
        when(userDetailsRepository.findByUserName(email)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class,()-> customerUserDetailsService.sendRecoveryCode(email));

        verify(recoveryCodeRepository,times(1)).findUserRecoveryCodesByEmail(any());
        verify(userDetailsRepository,times(1)).findByUserName(any());
        verify(recoveryCodeRepository,times(0)).save(any());
        verify(mailIntegration,times(0)).send(any(),any(),any());
    }
    @Test
    void testSendRecoveryCodeIfUserRecoveryCodeIsEmptyAndTheUserIsFound() {
        when(recoveryCodeRepository.findUserRecoveryCodesByEmail(email)).thenReturn(Optional.empty());
        when(userDetailsRepository.findByUserName(email)).thenReturn(Optional.of(userCredentials));

        var expectedMessage =  MessageDTO.builder()
                .httpStatus(HttpStatus.OK)
                .message("enviado com sucesso")
                .statusCode(HttpStatus.OK.value())
                .build();
        var actualMessage = customerUserDetailsService.sendRecoveryCode(email);

        Assertions.assertEquals(expectedMessage,actualMessage);
        verify(recoveryCodeRepository,times(1)).findUserRecoveryCodesByEmail(any());
        verify(userDetailsRepository,times(1)).findByUserName(any());
        verify(recoveryCodeRepository,times(1)).save(any());
        verify(mailIntegration,times(1)).send(any(),any(),any());
    }
    void testSendRecoveryCodeIfUserRecoveryCodeIsNotEmpty() {
        when(recoveryCodeRepository.findUserRecoveryCodesByEmail(email)).thenReturn(Optional.of(userRecoveryCode));

        var expectedMessage =  MessageDTO.builder()
                .httpStatus(HttpStatus.OK)
                .message("enviado com sucesso")
                .statusCode(HttpStatus.OK.value())
                .build();
        var actualMessage = customerUserDetailsService.sendRecoveryCode(email);

        Assertions.assertEquals(expectedMessage,actualMessage);
        verify(recoveryCodeRepository,times(1)).findUserRecoveryCodesByEmail(any());
        verify(userDetailsRepository,times(1)).findByUserName(any());
        verify(recoveryCodeRepository,times(1)).save(any());
        verify(mailIntegration,times(1)).send(any(),any(),any());
    }

    @Test
    void loadUserByUsername() {
    }
}