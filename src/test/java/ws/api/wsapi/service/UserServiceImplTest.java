package ws.api.wsapi.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.model.jpa.User;
import ws.api.wsapi.model.jpa.UserType;
import ws.api.wsapi.repositories.jpa.UserRepository;
import ws.api.wsapi.repositories.jpa.UserTypeRepository;
import ws.api.wsapi.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserTypeRepository userTypeRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void getAll() {

    }

    @Test
    void getUserById() {
    }

    @Test
    void return_the_user_created_if_the_idDoesNotExist() {
       var userType = new UserType(1L,"admin","acesso total");
       var userDto = new UserDto(){{
          setId(null);
          setName("carlos davi");
          setEmail("carlosdavi090787@gmail.com");
          setUserType(1L);
       }};
       var userTypeOptional = userTypeRepository.findById(1L);
       var user = new User(){{
           setName(userDto.getName());
           setEmail(userDto.getEmail());
           setUserType(userType);
           setDtSubscription(userDto.getDtSubscription());
           setDtExpiration(userDto.getDtExpiration());
       }};

        Mockito.when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user,userService.createUser(userDto));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}