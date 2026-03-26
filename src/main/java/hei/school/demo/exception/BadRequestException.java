package hei.school.demo.exception;

// On hérite de RuntimeException pour que Spring puisse l'attraper facilement
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
