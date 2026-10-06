package org.saud.peoplehub.dto.response;

import java.time.LocalDateTime;
public class ErrorResponse {

    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String path, int status, LocalDateTime timestamp) {
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = timestamp;
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
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorResponse{");
        sb.append("status=").append(status);
        sb.append(", message=").append(message);
        sb.append(", path=").append(path);
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }

    

}