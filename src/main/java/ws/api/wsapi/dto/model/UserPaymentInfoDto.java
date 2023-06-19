package ws.api.wsapi.dto.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPaymentInfoDto {
    private Long id;
    @Size(min = 16,max = 16,message = "deve conter 16 caracteres")
    private String cardNumber;
    @Size(min = 1,max = 12,message = "deve ser entre 1 e 12")
    private Long cardExpirationMonth;
    private Long cardExpirationYear;
    private String cardSecurityCode;
    private BigDecimal price;
    private Long instalments;
    private LocalDate dtPayment = LocalDate.now();
    @NotNull(message = "não pode ser nulo")
    private Long user;
}
