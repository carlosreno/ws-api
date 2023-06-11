package ws.api.wsapi.integration.impl;

import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.Payment;
import ws.api.wsapi.integration.WsRaspayIntegration;

public class WsRaspayIntegrationImpl implements WsRaspayIntegration {
    @Override
    public CostumerDto createCostumer(CostumerDto dto) {
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto dto) {
        return null;
    }

    @Override
    public Boolean processPayment(Payment payment) {
        return false;
    }
}
