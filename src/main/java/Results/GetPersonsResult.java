package Results;
import Models.PersonModel;
import java.util.ArrayList;

/** Returns the result of getting an person (or multiple data). */
public class GetPersonsResult extends Result{
    /** An array of Persons to return to the user. */
    private ArrayList<PersonModel> data;

    /** Initializes if multiple data were requested. */
    public GetPersonsResult(ArrayList<PersonModel> data) {
        this.data = data;
        success = true;
    }
    /** Initializes in case of error. */
    public GetPersonsResult(String message) { super(message); }

    public ArrayList<PersonModel> getPersons() { return data; }

    public void setPersons(ArrayList<PersonModel> data) { this.data = data; }
}
