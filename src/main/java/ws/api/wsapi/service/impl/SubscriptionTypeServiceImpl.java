package ws.api.wsapi.service.impl;

import org.springframework.stereotype.Service;
import ws.api.wsapi.dto.model.SubscriptionTypeDTO;
import ws.api.wsapi.exception.BadRequestException;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.mapper.SubscriptionTypeMapper;
import ws.api.wsapi.model.SubscriptionType;
import ws.api.wsapi.repositories.SubscriptionTypeRepository;
import ws.api.wsapi.service.SubscriptionTypeService;

import java.util.List;
import java.util.Objects;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;
    SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository){
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }
    @Override
    public List<SubscriptionType> finAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        return subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubscriptionType not found with ID: " + id));
    }
    @Override
    public SubscriptionType create(SubscriptionTypeDTO dto) {
            if (Objects.nonNull(dto.getId())){
                throw new BadRequestException("o campo id deve ser nulo");
            }
            return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(null,dto));

    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDTO dto) {
        findById(id);
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(id,dto));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        subscriptionTypeRepository.deleteById(id);
    }
}
