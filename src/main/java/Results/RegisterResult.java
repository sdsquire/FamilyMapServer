package Results;

/** Gives the result of a user's register. */
public class RegisterResult extends Result{
    /** The username of the user. */
    private String username;

    /** Initializes in case of success. */
    public RegisterResult(String authtoken, String username, String personID) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        success = true;
    }
    /** Initializes in case of error. */
    public RegisterResult(String message) { super(message); }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}