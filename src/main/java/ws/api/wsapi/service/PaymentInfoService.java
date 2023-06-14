package ws.api.wsapi.service;

import ws.api.wsapi.dto.PaymentProcessDto;

public interface PaymentInfoService {
    boolean process(PaymentProcessDto dto);
}
