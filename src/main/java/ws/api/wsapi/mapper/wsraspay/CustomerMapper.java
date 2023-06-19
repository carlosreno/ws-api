package ws.api.wsapi.mapper.wsraspay;

import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.model.jpa.User;

public class CustomerMapper {
    public static CostumerDto build(User user){

        var fullName = user.getName().split(" ");
        var firtName = fullName[0];
        var lastName =  firtName.length() > 1 ?fullName[fullName.length - 1]: "";
        return CostumerDto.builder()
                .email(user.getEmail())
                .cpf(user.getCpf())
                .firstName(firtName)
                .lastName(lastName)
                .build();
    }
}
