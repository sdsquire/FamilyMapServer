package Results;

public class Result {
    /** An optional message to the user. */
    protected String message;
    /** Indicates whether the clear was a success. */
    protected boolean success;
    /** Authoken used by some Results. */
    protected String authtoken = null;
    /** PersonID used by some Results. */
    protected String personID = null;

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
    public String getAuthtoken() { return authtoken; }
    public String getPersonID() { return personID; }

    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setAuthtoken(String authtoken) { this.authtoken = authtoken; }
    public void setPersonID(String personID) { this.personID = personID; }
}