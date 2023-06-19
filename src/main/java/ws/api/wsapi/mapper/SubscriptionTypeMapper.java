package ws.api.wsapi.mapper;

import lombok.experimental.UtilityClass;
import ws.api.wsapi.dto.model.SubscriptionTypeDTO;
import ws.api.wsapi.model.jpa.SubscriptionType;

@UtilityClass
public class SubscriptionTypeMapper {
    public static SubscriptionType fromDtoToEntity(Long id, SubscriptionTypeDTO dto){
        return SubscriptionType.builder()
                .id(id)
                .name(dto.getName())
                .accessMonths(dto.getAccessMonths())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build();
    }
}
