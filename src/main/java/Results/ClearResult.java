package Results;

/**
 * Indicates the result of the user's request to clear the database.
 */
public class ClearResult {
    /**
     * An optional message to the user.
     */
    private String message;
    /**
     * Indicates whether the clear was a success.
     */
    private boolean success;

    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
}

