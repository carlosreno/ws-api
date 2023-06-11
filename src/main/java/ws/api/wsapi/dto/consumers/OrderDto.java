package ws.api.wsapi.dto.consumers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String customerId;
    private Integer discount;
    private String productAcronym;
}
