package Results;

/**
 * Indicates the result of the user's request to load the database with new data.
 */
public class LoadResult {
    /**
     * A message to the user to indicate success.
     */
    String message;
    /**
     * Indicates whether the load was a success.
     */
    boolean success;

    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
}
