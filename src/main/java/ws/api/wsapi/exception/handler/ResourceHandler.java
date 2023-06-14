package ws.api.wsapi.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ws.api.wsapi.dto.error.ErrorDTO;
import ws.api.wsapi.exception.BadRequestException;
import ws.api.wsapi.exception.BusinessException;
import ws.api.wsapi.exception.NotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> notFoundException(NotFoundException n){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(ErrorDTO.builder()
                        .httpStatus(httpStatus)
                        .message(Collections.singletonList(n.getMessage()))
                        .statusCode(httpStatus.value())
                .build());
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> businessException(BusinessException n){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(ErrorDTO.builder()
                .httpStatus(httpStatus)
                .message(Collections.singletonList(n.getMessage()))
                .statusCode(httpStatus.value())
                .build());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> notFoundException(BadRequestException n) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(ErrorDTO.builder()
                .httpStatus(httpStatus)
                .message(Collections.singletonList(n.getMessage()))
                .statusCode(httpStatus.value())
                .build());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage())
                .toList();

        return ResponseEntity.badRequest().body(ErrorDTO.builder()
                .httpStatus(httpStatus)
                .message(errors)
                .statusCode(httpStatus.value())
                .build());
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        // Lógica de tratamento da exceção
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO.builder()
                        .message(Collections.singletonList(ex.getMessage()))
                        .httpStatus(httpStatus)
                        .statusCode(httpStatus.value())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Lógica de tratamento da exceção
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(ErrorDTO.builder()
                .message(Collections.singletonList(ex.getMessage()))
                .httpStatus(httpStatus)
                .statusCode(httpStatus.value())
                .build());
    }
}
