package ws.api.wsapi.integrationRas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.CreditCardDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.Payment;
import ws.api.wsapi.integration.RaspayFeignClient;
import ws.api.wsapi.integration.WsRaspayIntegration;
import ws.api.wsapi.integration.impl.WsRaspayIntegrationImpl;

import java.math.BigDecimal;

@SpringBootTest
public class WsRasPayIntegrationTest {
    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;
    @Autowired
    private RaspayFeignClient raspayFeignClient;
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
        var creditCardDto = new CreditCardDto(123L,"123456789",5L,6L,"1234123412341234",2031L);
        Payment paymentDto = new Payment(creditCardDto,"648730ad223e794f1fa0d9db","6487609a223e794f1fa0d9df");
        var dtoRas = raspayFeignClient.processPayment(paymentDto);
        System.out.println(dtoRas);
    }
}
