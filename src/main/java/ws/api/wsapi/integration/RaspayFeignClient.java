package ws.api.wsapi.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.PaymentDto;
import ws.api.wsapi.integration.configuration.WsRaspayIntegrationConfig;

@FeignClient(name = "${ws-ras.name}", url = "${ws-ras.url}",
             configuration = WsRaspayIntegrationConfig.class)
public interface RaspayFeignClient {

    @PostMapping("/customer")
    CostumerDto createCostumer(@RequestBody CostumerDto dto);
    @PostMapping("/order")
    OrderDto createOrder (@RequestBody OrderDto dto);
    @PostMapping("/payment/credit-card/")
    Boolean processPayment(@RequestBody PaymentDto payment);

}
