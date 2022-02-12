package Results;

import Models.PersonModel;

import java.util.ArrayList;

/**
 * Returns the result of getting a person (or multiple persons).
 */
public class GetPersonResult {
    /**
     * An array of Persons to return to the user.
     */
    private ArrayList<PersonModel> persons;
    /**
     * A single person, if the user requested one person.
     */
    private PersonModel person;
    /**
     * Indicates whether the getPerson was a success.
     */
    private boolean success = true;
    /**
     * Passes a message to the user in case of error.
     */
    private String message;

    /**
     * Initializes if one person was requested.
     */
    public GetPersonResult(PersonModel person) {
        this.person = person;
        success = true;
    }
    /**
     * Initializes if multiple persons were requested.
     */
    public GetPersonResult(ArrayList<PersonModel> persons) {
        this.persons = persons;
        success = true;
    }
    /**
     * Initializes in case of error.
     */
    public GetPersonResult(String message) {
        this.message = message;
        success = false;
    }

    public ArrayList<PersonModel> getPersons() { return persons; }
    public PersonModel getPerson() { return person; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }

    public void setPersons(ArrayList<PersonModel> persons) { this.persons = persons; }
    public void setPerson(PersonModel person) { this.person = person; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
}
