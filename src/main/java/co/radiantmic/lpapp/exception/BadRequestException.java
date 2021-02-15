package co.radiantmic.lpapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private int status;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    public BadRequestException(String message) {

        super(message);
    }

    public BadRequestException(int status, String message, LocalDateTime timeStamp) {

        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    @Override
    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public LocalDateTime getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {

        this.timeStamp = timeStamp;
    }
}
