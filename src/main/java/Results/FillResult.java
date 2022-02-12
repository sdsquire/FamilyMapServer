package Results;

/**
 * Indicates the result of a user's request to fill their family tree.
 */
public class FillResult {
    /**
     * A message to indicate success.
     */
     private String message;
    /**
     * Indicates whether the fill was a success.
     */
    private boolean success;

    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
}
