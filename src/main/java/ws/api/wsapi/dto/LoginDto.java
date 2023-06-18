package ws.api.wsapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String passWord;

}
