package ws.api.wsapi.dto.consumers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private CreditCardDto creditCard;
    private String customerId;
    private String orderId;
}
