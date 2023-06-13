package ws.api.wsapi.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.Payment;
import ws.api.wsapi.integration.configuration.WsRaspayIntegrationConfig;


public interface WsRaspayIntegration {

    CostumerDto createCostumer(CostumerDto dto);
    OrderDto createOrder (OrderDto dto);
    Boolean processPayment(Payment payment);
}
