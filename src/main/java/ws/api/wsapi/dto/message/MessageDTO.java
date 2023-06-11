package ws.api.wsapi.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class MessageDTO {

    private String message;
    private HttpStatus httpStatus;
    private Integer statusCode;
}
