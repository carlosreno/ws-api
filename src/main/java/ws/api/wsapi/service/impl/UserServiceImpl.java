package ws.api.wsapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.exception.BadRequestException;
import ws.api.wsapi.mapper.UserMapper;
import ws.api.wsapi.model.jpa.User;
import ws.api.wsapi.repositories.jpa.UserRepository;
import ws.api.wsapi.repositories.jpa.UserTypeRepository;
import ws.api.wsapi.service.UserService;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    @Override
    public User createUser(UserDto dto) {
        var optUserType = userTypeRepository.findById(dto.getUserType());
        if (optUserType.isEmpty()){
            throw new BadRequestException("userTypeId não encontrado");
        }
        var userType = optUserType.get();
        var user = UserMapper.fromDtoToEntity(dto,userType,null);
        return userRepository.save(user);
    }
}
