package ws.api.wsapi.mapper;

import lombok.experimental.UtilityClass;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.model.SubscriptionType;
import ws.api.wsapi.model.User;
import ws.api.wsapi.model.UserType;

@UtilityClass
public class UserMapper {
    public static UserDto fromEntityToDto(User user){
        return UserDto.builder()
                .name(user.getName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .phone(user.getPhone())
                .userType(user.getUserType().getId())
                .dtSubscription(user.getDtSubscription())
                .dtExpiration(user.getDtExpiration())
                .subscriptionsTypeId(user.getSubscriptionsTypeId().getId())
                .build();
    }
    public static User fromDtoToEntity(UserDto dto, UserType userType, SubscriptionType subscriptionType){
        return User.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .userType(userType)
                .dtSubscription(dto.getDtSubscription())
                .dtExpiration(dto.getDtExpiration())
                .subscriptionsTypeId(subscriptionType)
                .build();
    }
}
