package ws.api.wsapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ws.api.wsapi.service.impl.CustomerUserDetailsService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private CustomerUserDetailsService userService;

    @Test
    void sendCode(){
        userService.sendRecoveryCode("davisarse@gmail.com");
    }
}
