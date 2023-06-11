package ws.api.wsapi.service;

import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getUserById();
    User createUser(UserDto dto);
    User updateUser(Long id,UserDto dto);
    void deleteUser(Long id);
}
