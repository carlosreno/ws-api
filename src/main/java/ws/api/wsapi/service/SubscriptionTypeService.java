package ws.api.wsapi.service;

import ws.api.wsapi.dto.model.SubscriptionTypeDTO;
import ws.api.wsapi.model.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> finAll();
    SubscriptionType findById(Long id);
    SubscriptionType create(SubscriptionTypeDTO subscriptionTypeDTO);

    SubscriptionType update(Long id,SubscriptionTypeDTO dto);

    void delete(Long id);
}