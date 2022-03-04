package Models;

/** Data Access Object modeling the structure of the SQL Person table. */
public class PersonModel{
    /** A unique identifier for a person; the primary key. */
    private String personID;
    /** The username associated with the person; a foreign key. */
    private String associatedUsername;
    /** The person's first name. */
    private String firstName;
    /** The person's last name. */
    private String lastName;
    /** The person's gender; must be 'm' or 'f'. */
    private String gender;
    /** The unique ID of the person's father; a foreign key. */
    private String fatherID;
    /** The unique ID of the person's mother; a foreign key. */
    private String motherID;
    /** The unique ID of the person's spouse; a foreign key. */
    private String spouseID;

    /** Initializes a person object; parameters are equivalent to data members. */
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
    public PersonModel(){}


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
        return gender.equals(that.gender) &&
                personID.equals(that.personID) &&
                associatedUsername.equals(that.associatedUsername) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                fatherID.equals(that.fatherID) &&
                motherID.equals(that.motherID) && spouseID.equals(that.spouseID);
    }
}
