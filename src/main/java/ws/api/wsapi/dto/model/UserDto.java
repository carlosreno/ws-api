package ws.api.wsapi.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private LocalDate dtSubscription = LocalDate.now();
    private LocalDate dtExpiration = LocalDate.now();
    private Long userType;
    private Long subscriptionsTypeId;
}
