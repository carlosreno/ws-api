package ws.api.wsapi;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ws.api.wsapi.model.jpa.UserType;
import ws.api.wsapi.repositories.jpa.UserTypeRepository;
import ws.api.wsapi.service.UserTypeService;
import ws.api.wsapi.service.impl.UserTypeServiceImpl;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserTypeServiceTest {

    @Mock
    private UserTypeRepository userTypeRepository;
    @InjectMocks
    private UserTypeServiceImpl userTypeServiceImpl;

    @Test
    void given_find_All_when_there_are_datas_in_database(){
        List<UserType> allTypes = Arrays.asList(
                new UserType(1L,"admin","acesso total"),
                new UserType(2L,"","teste"),
                new UserType());
        System.out.println(allTypes);
        Mockito.when(userTypeRepository.findAll()).thenReturn(allTypes);
        var result = userTypeServiceImpl.findAll();
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result).hasSize(3);
    }
}
