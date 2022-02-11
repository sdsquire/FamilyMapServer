package Models;

public class AuthtokenModel {
    private String authtoken;
    private String username;

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
                username.equals(that.authtoken);

    }
}