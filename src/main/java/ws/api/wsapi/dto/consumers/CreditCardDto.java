package ws.api.wsapi.dto.consumers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.web.JsonPath;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDto {
    private Long cvv;
    @CPF
    private String documentNumber;
    @Min(1)@Max(12)
    private Long installments;
    @NotBlank
    private Long month;
    @Min(16)@Max(16)
    private String number;
    @NotBlank
    private Long year;
}
