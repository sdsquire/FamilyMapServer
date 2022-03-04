package Results;
import Models.PersonModel;
import java.util.ArrayList;

/** Returns the result of getting an person (or multiple persons). */
public class GetPersonsResult extends Result{
    /** An array of Persons to return to the user. */
    private ArrayList<PersonModel> persons;
    /** A single person, if the user requested one person. */
    private PersonModel person;

    /** Initializes if multiple persons were requested. */
    public GetPersonsResult(ArrayList<PersonModel> persons) {
        if (persons.size() == 1)
            this.person = persons.get(0);
        else
            this.persons = persons;
        success = true;
    }
    /** Initializes in case of error. */
    public GetPersonsResult(String message) { super(message); }

    public ArrayList<PersonModel> getPersons() { return persons; }
    public PersonModel getPerson() { return person; }

    public void setPersons(ArrayList<PersonModel> persons) { this.persons = persons; }
    public void setPerson(PersonModel person) { this.person = person; }
}
