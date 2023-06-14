package ws.api.wsapi.mapper.wsraspay;

import lombok.experimental.UtilityClass;
import ws.api.wsapi.dto.consumers.CreditCardDto;
import ws.api.wsapi.dto.consumers.PaymentDto;
@UtilityClass
public class PaymentMapper {
    public static PaymentDto build(String customerId, String orderId, CreditCardDto dto){
        return PaymentDto.builder()
                .customerId(customerId)
                .orderId(orderId)
                .creditCard(dto)
                .build();
    }
}
