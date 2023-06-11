package ws.api.wsapi.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private List<String> message;
    private HttpStatus httpStatus;
    private Integer statusCode;


}
