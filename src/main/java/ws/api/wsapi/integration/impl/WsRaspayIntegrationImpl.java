package ws.api.wsapi.integration.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.Payment;
import ws.api.wsapi.integration.WsRaspayIntegration;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration{
    @Value("${ws-ras.url}")
    private String url;

    private RestTemplate restTemplate;
    WsRaspayIntegrationImpl(){
        restTemplate = new RestTemplate();
    }
    public CostumerDto createCostumer(CostumerDto dto) {
        try {
            var request = new HttpEntity<CostumerDto>(dto);
            var response = restTemplate.exchange(url, HttpMethod.POST,request,CostumerDto.class);
            return response.getBody();
        }catch (Exception e){
            throw e;
        }
    }
    public OrderDto createOrder(OrderDto dto) {
        return null;
    }
    public Boolean processPayment(Payment payment) {
        return null;
    }
}
