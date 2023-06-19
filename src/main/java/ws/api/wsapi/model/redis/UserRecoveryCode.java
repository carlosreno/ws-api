package ws.api.wsapi.model.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@RedisHash("recoveryCode")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRecoveryCode {
    @Id
    private String id;
    private String email;
    private String code;
    private LocalDateTime createDate = LocalDateTime.now();
}
