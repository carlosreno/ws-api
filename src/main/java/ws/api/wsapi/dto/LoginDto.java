package ws.api.wsapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
