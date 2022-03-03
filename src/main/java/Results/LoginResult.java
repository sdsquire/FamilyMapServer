package Results;

/** A result of logging in, which returns an authorization token to the user. */
public class LoginResult extends Result{
    /** The authorization token associated with the user. */
    private String authtoken;
    /** The user's username. */
    private String username;
    /** The personID assigned to the user. */
    private String personID;

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

    public String getAuthtoken() { return authtoken; }
    public String getUsername() { return username; }
    public String getPersonID() { return personID; }

    public void setAuthtoken(String authtoken) { this.authtoken = authtoken; }
    public void setUsername(String username) { this.username = username; }
    public void setPersonID(String personID) { this.personID = personID; }
}

