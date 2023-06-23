package ws.api.wsapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRecovery {
    @Email(message = "invalido")
    private String email;
    @NotBlank(message = "não pode ser vazio")
    private String password;
    private String recoveryCode;
}
