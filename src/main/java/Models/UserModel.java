package Models;

public class UserModel {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

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
                gender == that.gender &&
                personID.equals(that.personID);
    }
}