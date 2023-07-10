package ws.api.wsapi.model.redis;

import jakarta.validation.constraints.Email;
import lombok.*;
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

    public boolean isCodeValid(){
        var limitTime = LocalDateTime.now().minusMinutes(1);
        return createDate.isAfter(limitTime);
    }
}
