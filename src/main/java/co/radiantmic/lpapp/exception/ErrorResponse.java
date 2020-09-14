package co.radiantmic.lpapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;

    public ErrorResponse() {

    }

    public ErrorResponse(int status, String message, LocalDateTime timeStamp) {

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
