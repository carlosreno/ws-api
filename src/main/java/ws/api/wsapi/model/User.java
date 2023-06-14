package ws.api.wsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nao pode ser branco")
    @Size(min = 9,max = 100)
    private String name;
    @Email(message = "invalido")
    private String email;
    @Size(min = 11)
    private String phone;
    @CPF
    private String cpf;
    @Column(name = "dt_subscription",nullable = false)
    private LocalDate dtSubscription;
    @Column(name = "dt_expiration",nullable = false)
    private LocalDate dtExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriptions_type_id")
    private SubscriptionType subscriptionsType;
}
