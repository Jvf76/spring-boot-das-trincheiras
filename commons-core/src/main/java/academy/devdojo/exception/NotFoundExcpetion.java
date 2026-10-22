package academy.devdojo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundExcpetion extends ResponseStatusException {
    public NotFoundExcpetion(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
