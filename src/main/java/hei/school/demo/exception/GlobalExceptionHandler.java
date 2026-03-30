package hei.school.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Cette annotation dit à Spring : "Surveille tous les contrôleurs"
public class GlobalExceptionHandler {

    // Cette méthode attrape toutes les erreurs de type RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Oups ! Une erreur est survenue : " + e.getMessage());
    }
}
