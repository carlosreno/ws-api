package ws.api.wsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ws.api.wsapi.dto.model.UserPaymentInfoDto;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessDto {
    @NotBlank(message = "deve ser informado")
    private String productKey;
    private BigDecimal discount;
    @NotNull(message = "dados de pagamento deve ser informado")
    @JsonProperty("userPaymentInfo")
    private UserPaymentInfoDto userPaymentInfoDto;
}
