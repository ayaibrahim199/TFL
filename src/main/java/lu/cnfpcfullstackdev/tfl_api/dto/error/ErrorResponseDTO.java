package lu.cnfpcfullstackdev.tfl_api.dto.error;

import java.time.LocalDateTime;

//DTO = data transfer object
//define what error response should look like
public class ErrorResponseDTO {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;

    public ErrorResponseDTO(int statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
//getters
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
