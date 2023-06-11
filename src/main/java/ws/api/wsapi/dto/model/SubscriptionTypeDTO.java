package ws.api.wsapi.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionTypeDTO {
    private Long id;
    private String name;
    private Long accessMonths;
    private BigDecimal price;
    private String productKey;
}
