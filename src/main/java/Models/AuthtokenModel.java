package Models;

/** Data Access Object modeling the structure of the SQL Authtoken table. */
public class AuthtokenModel {
    /** The unique authtoken generated upon login; the primary key. */
    private String authtoken;
    /** The username associated with this authtoken; a foreign key. */
    private String username;

    /** Initializes an authtoken object; parameters equivalent to data members */
    public AuthtokenModel(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public String getAuthtoken() { return authtoken; }
    public String getUsername() { return username; }

    public void setAuthtoken(String authtoken) { this.authtoken = authtoken; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        AuthtokenModel that = (AuthtokenModel) o;
        return authtoken.equals(that.authtoken) &&
                username.equals(that.username);

    }
}