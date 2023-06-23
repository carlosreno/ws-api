package ws.api.wsapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.api.wsapi.model.jpa.UserType;
import ws.api.wsapi.repositories.jpa.UserTypeRepository;
import ws.api.wsapi.service.UserTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {
    private final UserTypeRepository userTypeRepository;
    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
}
