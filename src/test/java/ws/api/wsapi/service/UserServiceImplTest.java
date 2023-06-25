package ws.api.wsapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import ws.api.wsapi.dto.model.UserDto;
import ws.api.wsapi.exception.BadRequestException;
import ws.api.wsapi.model.jpa.User;
import ws.api.wsapi.model.jpa.UserType;
import ws.api.wsapi.repositories.jpa.UserRepository;
import ws.api.wsapi.repositories.jpa.UserTypeRepository;
import ws.api.wsapi.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserTypeRepository userTypeRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private UserDto userDto;
    @BeforeEach
    public void loadUser(){
        userDto = new UserDto(){{
            setName("carlos davi");
            setEmail("carlosdavi090787@gmail.com");
            setUserType(1L);
        }};
    }

    @Test
    void return_the_user_created_if_the_idIsNull() {

        var userType = new UserType(1L,"admin - teste","acesso total - teste");
        Mockito.when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));
        var userTypeOptional = userTypeRepository.findById(1L);
       var user = new User(){{
           setName(userDto.getName());
           setEmail(userDto.getEmail());
           setUserType(userTypeOptional.get());
           setDtSubscription(userDto.getDtSubscription());
           setDtExpiration(userDto.getDtExpiration());
       }};

        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(user,userService.createUser(userDto));
    }
    @Test
    void return_the_user_created_if_the_idIsNull_ThrowBadRequestException_UserTypeInvalid() {

        var userType = new UserType(1L,"admin - teste","acesso total - teste");
        Mockito.when(userTypeRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class,()-> userService.createUser(userDto));
        verify(userTypeRepository,times(1)).findById(any());
        verify(userRepository,times(0)).save(any());

    }


}