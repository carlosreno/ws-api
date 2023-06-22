package ws.api.wsapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ws.api.wsapi.integration.MailIntegration;
import ws.api.wsapi.repositories.redis.UserRecoveryCodeRepository;
import ws.api.wsapi.service.impl.CustomerUserDetailsService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private CustomerUserDetailsService userService;
    @Autowired
    private UserRecoveryCodeRepository recoveryCodeRepository;
    @Autowired
    private MailIntegration mailIntegration;

    @Test
    void sendCode(){
        userService.sendRecoveryCode("davisarse@gmail.com");
    }
    @Test
    void getRecoveryByEmail(){
        var optRec = recoveryCodeRepository.findUserRecoveryCodesByEmail("5325");

        if (optRec.isPresent()){
            System.out.println("deu bom" +optRec);
        }else {
            System.out.println("gg");
        }
    }

}
