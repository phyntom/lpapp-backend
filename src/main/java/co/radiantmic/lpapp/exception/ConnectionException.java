package co.radiantmic.lpapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ConnectionException extends RuntimeException {

    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }
}
