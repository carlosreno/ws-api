package ws.api.wsapi.mapper.wsraspay;

import ws.api.wsapi.dto.PaymentProcessDto;
import ws.api.wsapi.dto.consumers.OrderDto;

public class OrderMapper {
    public static OrderDto build(String customerId, PaymentProcessDto paymentProcessDto){
        return OrderDto.builder()
                .customerId(customerId)
                .productAcronym(paymentProcessDto.getProductKey())
                .discount(paymentProcessDto.getDiscount())
                .build();
    }
}
