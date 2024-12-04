package livro.api.infra;

import jakarta.persistence.EntityNotFoundException;
import livro.api.dto.ErrorResponse;
import livro.api.exception.ValidacaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErrorResponse> handleValidacaoException(ValidacaoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Não é possível excluir o registro, pois ele está relacionado a outros registros.";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }



}
