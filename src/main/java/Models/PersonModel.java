package Models;

public class PersonModel{
    private String personID;
    private String associatedUsername;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    public PersonModel(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public String getPersonID() { return personID; }
    public String getAssociatedUsername() { return associatedUsername; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getFatherID() { return fatherID; }
    public String getMotherID() { return motherID; }

    public void setPersonID(String personID) { this.personID = personID; }
    public void setAssociatedUsername(String associatedUsername) { this.associatedUsername = associatedUsername; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setFatherID(String fatherID) { this.fatherID = fatherID; }
    public void setMotherID(String motherID) { this.motherID = motherID; }
    public void setSpouseID(String spouseID) { this.spouseID = spouseID; }
    public String getSpouseID() { return spouseID; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        PersonModel that = (PersonModel) o;
        return gender == that.gender &&
                personID.equals(that.personID) &&
                associatedUsername.equals(that.associatedUsername) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                fatherID.equals(that.fatherID) &&
                motherID.equals(that.motherID) && spouseID.equals(that.spouseID);
    }
}
