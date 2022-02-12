package Results;

/**
 * Gives the result of a user's register.
 */
public class RegisterResult {
    /**
     * The authtoken assigned to the user.
     */
    private String authtoken;
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The personID assigned to the user.
     */
    private String personID;
    /**
     * An optional message for the user, in case of failure.
     */
    private String message;
    /**
     * Indicates whether the registration was a success.
     */
    boolean success;

    /**
     * Initializes in case of success.
     */
    public RegisterResult(String authtoken, String username, String personID) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        success = true;
    }
    /**
     * Initializes in case of error.
     */
    public RegisterResult(String message) {
        this.message = message;
        success = false;
    }

    public String getAuthtoken() { return authtoken; }
    public String getUsername() { return username; }
    public String getPersonID() { return personID; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public void setAuthtoken(String authtoken) { this.authtoken = authtoken; }
    public void setUsername(String username) { this.username = username; }
    public void setPersonID(String personID) { this.personID = personID; }
    public void setMessage(String message) { this.message = message; }
    public void setSuccess(boolean success) { this.success = success; }
}