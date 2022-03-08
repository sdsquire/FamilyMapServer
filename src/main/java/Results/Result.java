package Results;

public class Result {
    /** An optional message to the user. */
    protected String message;
    /** Indicates whether the clear was a success. */
    protected boolean success;

    public Result() {
        this.message = null;
        this.success = false;
    }

    public Result(String message) {
        this.message = "error: " + message;
        this.success = false;
    }

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
}