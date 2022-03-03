package Results;

import Models.PersonModel;
import java.util.ArrayList;

/** Returns the result of getting a person (or multiple persons). */
public class GetPersonResult extends Result{
    /** An array of Persons to return to the user. */
    private ArrayList<PersonModel> persons;
    /** A single person, if the user requested one person. */
    private PersonModel person;

    /** Initializes if one person was requested. */
    public GetPersonResult(PersonModel person) {
        this.person = person;
        success = true;
    }
    /** Initializes if multiple persons were requested. */
    public GetPersonResult(ArrayList<PersonModel> persons) {
        this.persons = persons;
        success = true;
    }
    /** Initializes in case of error. */
    public GetPersonResult(String message) {
        this.message = message;
        success = false;
    }

    public ArrayList<PersonModel> getPersons() { return persons; }
    public PersonModel getPerson() { return person; }

    public void setPersons(ArrayList<PersonModel> persons) { this.persons = persons; }
    public void setPerson(PersonModel person) { this.person = person; }
}
