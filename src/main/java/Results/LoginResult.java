package Results;

/** A result of logging in, which returns an authorization token to the user. */
public class LoginResult extends Result{
    /** The user's username. */
    private String username;

    /** Initializes in case of success. */
    public LoginResult(String authtoken, String username, String personID) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        success = true;
    }

    public LoginResult(String message) {
        super(message);
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}

