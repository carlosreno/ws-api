package ws.api.wsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "user_payment_info")
public class UserPaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_payment_info_id")
    private Long id;
    private String cardNumber;
    private Long cardExpirationMonth;
    private Long cardExpirationYear;
    private String cardSecurityCode;
    private BigDecimal price;
    private Long instalments;
    private LocalDate dtPayment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
