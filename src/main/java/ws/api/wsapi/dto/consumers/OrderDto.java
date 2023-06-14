package ws.api.wsapi.dto.consumers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private String id;
    private String customerId;
    private BigDecimal discount;
    private String productAcronym;
}
