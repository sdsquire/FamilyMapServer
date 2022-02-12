package Results;

/**
 * A result of logging in, which returns an authorization token to the user.
 */
public class LoginResult {
    /**
     * The authorization token associated with the user.
     */
    private String authtoken;
    /**
     * The user's username.
     */
    private String username;
    /**
     * The personID assigned to the user.
     */
    private String personID;
    /**
     * An optional message passed to the user.
     */
    private String message;
    /**
     * Indicates whether login was a success.
     */
    private boolean success;

    /**
     * Initializes in case of success.
     */
    public LoginResult(String authtoken, String username, String personID) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        success = true;
    }
    /**
     * Initializes in case of error.
     */
    public LoginResult(String message) {
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

