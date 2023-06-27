package ws.api.wsapi.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
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

import java.time.LocalDateTime;
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
    @Mock
    private UserRecoveryCode userRecoveryCode;
    private UserRecoveryCode userRecoveryCodeIsInvalid = mock(UserRecoveryCode.class);;

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
        userRecoveryCodeIsInvalid = new UserRecoveryCode(){{
            setId("teste1");
            setEmail(email);
            setCode("1234");
            setCreateDate(LocalDateTime.now().minusMinutes(3));
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
    @Test
    void testSendRecoveryCode_IfUserRecoveryCodeIsNotEmptyAndTimeInvalid() {
        when(recoveryCodeRepository.findUserRecoveryCodesByEmail(userRecoveryCodeIsInvalid.getEmail())).thenReturn(Optional.of(userRecoveryCodeIsInvalid));
        ArgumentCaptor<UserRecoveryCode> captor = ArgumentCaptor.forClass(UserRecoveryCode.class);

        doAnswer((Answer<UserRecoveryCode>) invocation -> {
            UserRecoveryCode modifiedUserRecoveryCode = captor.getValue();
            assertNotEquals(modifiedUserRecoveryCode,userRecoveryCodeIsInvalid);
            assertNotEquals(modifiedUserRecoveryCode.getCode(),userRecoveryCodeIsInvalid.getCode());
            // Faça as verificações desejadas no modifiedUserRecoveryCode
            // Exemplo: assertEquals(expectedValue, modifiedUserRecoveryCode.getPropriedade());

            return null;
        }).when(recoveryCodeRepository).save(captor.capture());

        var expectedMessage =  MessageDTO.builder()
                .httpStatus(HttpStatus.OK)
                .message("enviado com sucesso")
                .statusCode(HttpStatus.OK.value())
                .build();
        var actualMessage = customerUserDetailsService.sendRecoveryCode(email);

        Assertions.assertEquals(expectedMessage,actualMessage);


        verify(recoveryCodeRepository,times(1)).findUserRecoveryCodesByEmail(any());
        verify(recoveryCodeRepository,times(1)).save(any());
        verify(mailIntegration,times(1)).send(any(),any(),any());
    }
    @Test
    void testSendRecoveryCode_IfUserRecoveryCodeIsNotEmptyAndTimeValid() {
        when(recoveryCodeRepository.findUserRecoveryCodesByEmail(userRecoveryCode.getEmail())).thenReturn(Optional.of(userRecoveryCode));
        ArgumentCaptor<UserRecoveryCode> captor = ArgumentCaptor.forClass(UserRecoveryCode.class);

        doAnswer((Answer<UserRecoveryCode>) invocation -> {
            UserRecoveryCode modifiedUserRecoveryCode = captor.getValue();
            assertEquals(modifiedUserRecoveryCode,userRecoveryCode);
            assertEquals(modifiedUserRecoveryCode.getCode(),userRecoveryCode.getCode());
            System.out.println(modifiedUserRecoveryCode.getCode()+""+userRecoveryCode.getCode());

            return null;
        }).when(recoveryCodeRepository).save(captor.capture());

        var expectedMessage =  MessageDTO.builder()
                .httpStatus(HttpStatus.OK)
                .message("enviado com sucesso")
                .statusCode(HttpStatus.OK.value())
                .build();
        var actualMessage = customerUserDetailsService.sendRecoveryCode(email);

        Assertions.assertEquals(expectedMessage,actualMessage);
        verify(recoveryCodeRepository,times(1)).findUserRecoveryCodesByEmail(any());
        verify(recoveryCodeRepository,times(1)).save(any());
        verify(mailIntegration,times(1)).send(any(),any(),any());
    }

    @Test
    void loadUserByUsername() {
    }
}