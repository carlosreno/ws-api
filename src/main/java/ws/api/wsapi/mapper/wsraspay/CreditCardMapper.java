package ws.api.wsapi.mapper.wsraspay;

import ws.api.wsapi.dto.consumers.CreditCardDto;
import ws.api.wsapi.dto.model.UserPaymentInfoDto;

public class CreditCardMapper {
    public static CreditCardDto build(UserPaymentInfoDto dto,String documentNumber){
        return CreditCardDto.builder()
                .documentNumber(documentNumber)
                .number(dto.getCardNumber())
                .month(dto.getCardExpirationMonth())
                .year(dto.getCardExpirationYear())
                .cvv(Long.parseLong(dto.getCardSecurityCode()))
                .installments(dto.getInstalments())
                .build();
    }
}
