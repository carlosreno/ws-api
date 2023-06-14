package ws.api.wsapi.integration;

import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.PaymentDto;


public interface WsRaspayIntegration {

    CostumerDto createCostumer(CostumerDto dto);
    OrderDto createOrder (OrderDto dto);
    Boolean processPayment(PaymentDto payment);
}
