package Models;

/** Data Access Object modeling the structure of the SQL User table. */
public class UserModel {
    /** The username for this user; the primary key. */
    private String username;
    /** The password associated with this username; requires 12 characters, a digit, symbol, whitespace character, arabic letter, and blood of a virgin. */
    private String password;
    /** The email associated with this user. */
    private String email;
    /** The user's first name. */
    private String firstName;
    /** The user's last name. */
    private String lastName;
    /** The user's gender; must be 'm' or 'f'. */
    private String gender;
    /** The person associated with this user; a foreign key. */
    private String personID;

    /** Initializes a user object; parameters equivalent to data members. */
    public UserModel(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String  getGender() { return gender; }
    public String getPersonID() { return personID; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setPersonID(String personID) { this.personID = personID; }

    @Override
    public boolean equals(Object o) {
        if (o== null || getClass() != o.getClass())
            return false;
        UserModel that = (UserModel) o;

        return username.equals(that.username) &&
                password.equals(that.password) &&
                email.equals(that.email) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                gender.equals(that.gender) &&
                personID.equals(that.personID);
    }
}