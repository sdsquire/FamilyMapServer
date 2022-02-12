package Requests;

/**
 * Requests a returning user to be logged in.
 */
public class LoginRequest {
    /**
     * The user's username.
     */
    private String username;
    /**
     * The user's password.
     */
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}
