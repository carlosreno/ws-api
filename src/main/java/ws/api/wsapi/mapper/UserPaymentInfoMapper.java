package ws.api.wsapi.mapper;

import lombok.experimental.UtilityClass;
import ws.api.wsapi.dto.model.UserPaymentInfoDto;
import ws.api.wsapi.model.jpa.User;
import ws.api.wsapi.model.jpa.UserPaymentInfo;

@UtilityClass
public class UserPaymentInfoMapper {
    public static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDto dto, User user){
        return UserPaymentInfo.builder()
                .id(dto.getId())
                .cardNumber(dto.getCardNumber())
                .cardExpirationMonth(dto.getCardExpirationMonth())
                .cardExpirationYear(dto.getCardExpirationYear())
                .cardSecurityCode(dto.getCardSecurityCode())
                .instalments(dto.getInstalments())
                .price(dto.getPrice())
                .dtPayment(dto.getDtPayment())
                .user(user)
                .build();
    }
}