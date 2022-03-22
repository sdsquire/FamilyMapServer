package Results;
import Models.PersonModel;

/** Returns the result of getting a person (or multiple persons). */
public class GetPersonResult extends Result {
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

    public GetPersonResult(PersonModel person) {
        this.personID = person.getPersonID();
        this.associatedUsername = person.getAssociatedUsername();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.fatherID = person.getFatherID();
        this.motherID = person.getMotherID();
        this.spouseID = person.getSpouseID();
        success = true;
    }
    public GetPersonResult(String message) { super(message); }

    public String getPersonID() { return personID; }
    public String getAssociatedUsername() { return associatedUsername; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getFatherID() { return fatherID; }
    public String getMotherID() { return motherID; }
    public String getSpouseID() { return spouseID; }

    public void setPersonID(String personID) { this.personID = personID; }
    public void setAssociatedUsername(String associatedUsername) { this.associatedUsername = associatedUsername; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setFatherID(String fatherID) { this.fatherID = fatherID; }
    public void setMotherID(String motherID) { this.motherID = motherID; }
    public void setSpouseID(String spouseID) { this.spouseID = spouseID; }

    @Override
    public boolean equals(Object o) {
        if (o == null || PersonModel.class != o.getClass())
            return false;
        PersonModel that = (PersonModel) o;
        return gender.equals(that.getGender()) &&
                personID.equals(that.getPersonID()) &&
                associatedUsername.equals(that.getAssociatedUsername()) &&
                firstName.equals(that.getFirstName()) &&
                lastName.equals(that.getLastName()) &&
                (fatherID == null && that.getFatherID() == null || fatherID.equals(that.getFatherID())) &&
                (motherID == null && that.getMotherID() == null || motherID.equals(that.getMotherID())) &&
                (spouseID == null && that.getSpouseID() == null || spouseID.equals(that.getSpouseID()));
    }
}
