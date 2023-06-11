package ws.api.wsapi.dto.consumers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerDto {
        private String id;
        private String cpf;
        private String email;
        private String firstName;
        private String lastName;
}
