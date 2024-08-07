package Requests;

/** Requests a user to be registered in the database. */
public class RegisterRequest {
    /** The user's desired username. */
    private String username;
    /** The user's desired password. */
    private String password;
    /** The user's email. */
    private String email;
    /** The user's first name. */
    private String firstName;
    /** The user's last name. */
    private String lastName;
    /** The user's gender; must be 'm' or 'f'. */
    private String gender;

    /** Initializes a register request; parameters equivalent to data members */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(String gender) { this.gender = gender; }
}