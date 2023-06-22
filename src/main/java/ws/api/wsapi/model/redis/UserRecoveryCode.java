package ws.api.wsapi.model.redis;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("recoveryCode")
public class UserRecoveryCode {
    @Id
    private String id;
    @Indexed
    @Email
    private String email;
    private String code;
    private LocalDateTime createDate = LocalDateTime.now();
}
