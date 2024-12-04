package livro.api.dto;

public class ErrorResponse {
    private final int status; // Imutável
    private final String message; // Imutável

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
