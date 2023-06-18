package ws.api.wsapi.integrationRas;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ws.api.wsapi.dto.JWTDto;
import ws.api.wsapi.dto.LoginDto;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.CreditCardDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.PaymentDto;
import ws.api.wsapi.integration.MailIntegration;
import ws.api.wsapi.integration.RaspayFeignClient;
import ws.api.wsapi.integration.WsRaspayIntegration;
import ws.api.wsapi.integration.impl.WsRaspayIntegrationImpl;
import ws.api.wsapi.service.AutenticationService;
import ws.api.wsapi.service.TokenService;

import java.math.BigDecimal;

@SpringBootTest
public class WsRasPayIntegrationTest {
    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;
    @Autowired
    private MailIntegration mailIntegration;
    @Autowired
    private RaspayFeignClient raspayFeignClient;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutenticationService autenticationService;
//    @Test
//    void createCustomerWhenDtoOK(){
//        var dto = new CostumerDto(null,"08142678314","carlosdavi090787@gmail.com","Carlos","Ferreira");
//        wsRaspayIntegration.createCostumer(dto);
//    }

    //CostumerDto(id=648730ad223e794f1fa0d9db, cpf=08142678314,
    //email=carlosdavi090787@gmail.com, firstName=Carlos, lastName=Ferreira)
    public String dtoRAS = "";
    @Test
    void createCustomerWhenDtoOK(){
        var dto = new CostumerDto(null,"08142678314","carlosdavi090787@gmail.com","Carlos","Ferreira");
        var dtoRAS = raspayFeignClient.createCostumer(dto);
        System.out.println(dtoRAS);
    }
    //OrderDto(id=64875918223e794f1fa0d9dd,
    //customerId=648730ad223e794f1fa0d9db,
    //discount=0, productAcronym=null)
    @Test
    void createOrderWhenDtoOK(){
        var dto = new OrderDto(null,"648730ad223e794f1fa0d9db", BigDecimal.ZERO,"MONTH22");
        var dtoRas = raspayFeignClient.createOrder(dto);
        System.out.println(dtoRas.toString());
    }
    @Test
    void processPaymentWhenDtoOK(){
            CreditCardDto creditCardDto = new CreditCardDto(123L,"1234567890",12L,2L,"1234123412341235",2030L);
            PaymentDto paymentDto = new PaymentDto(creditCardDto,"648730ad223e794f1fa0d9db","6489a45f9c08077cfa7ba0eb");
            System.out.println(paymentDto);
            var boo = raspayFeignClient.processPayment(paymentDto);
        System.out.println(boo);
    }
    @Test
    void sendMail(){
        mailIntegration.send("carlosdavi090787@gmail.com","teste service mail","acesso liberado");
    }

    @Test
    void auth(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("carlos","123456");
        Authentication userAuth= authenticationManager.authenticate(authenticationToken);
        String jwtDto = tokenService.getToken(userAuth);
        System.out.println(jwtDto);
    }
    @Test
    void authService(){
        JWTDto token = autenticationService.auth(new LoginDto("carlos","123456"));
        System.out.println(token);
    }

}
