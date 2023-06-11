package ws.api.wsapi.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.Payment;

@FeignClient(name = "ws-ras")
public interface WsRaspayIntegration {
    @PostMapping(name = "/customer")
    CostumerDto createCostumer(CostumerDto dto);
    OrderDto createOrder (OrderDto dto);
    Boolean processPayment(Payment payment);
}
