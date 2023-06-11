package ws.api.wsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions_type")
@Builder
public class SubscriptionType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptions_type_id")
    private Long id;

    @NotBlank(message = "não pode ser vazio")
    private String name;

    @Column(name = "access_months")
    @Max(value = 12,message = "não pode ser maior que 12")
    private Long accessMonths;

    @NotNull(message = "não pode ser nulo")
    private BigDecimal price;

    @Column(name = "product_key",unique = true)
    @NotBlank(message = "não pode ser vazio")
    @Size(min = 5,max = 15)
    private String productKey;

}
