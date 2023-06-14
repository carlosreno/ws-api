package ws.api.wsapi.service.impl;

import org.springframework.stereotype.Service;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.exception.BadRequestException;
import ws.api.wsapi.mapper.UserMapper;
import ws.api.wsapi.model.User;
import ws.api.wsapi.repositories.UserRepository;
import ws.api.wsapi.repositories.UserTypeRepository;
import ws.api.wsapi.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository){
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }
    @Override
    public List<User> getAll() {
//        List<UserDto> dto = userRepository.findAll().stream()
//                .map()
//        return userRepository.findAll();
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserById() {
        return null;
    }

    @Override
    public User createUser(UserDto dto) {
        var optUserType = userTypeRepository.findById(dto.getUserType());
        if (optUserType.isEmpty()){
            throw new BadRequestException("userTypeId não encontrado");
        }
        var userType = optUserType.get();
        var user = UserMapper.fromDtoToEntity(dto,userType,null);
        System.out.println(user.toString());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto dto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
