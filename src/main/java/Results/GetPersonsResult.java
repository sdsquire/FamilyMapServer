package Results;
import Models.PersonModel;
import java.util.ArrayList;

/** Returns the result of getting an person (or multiple data). */
public class GetPersonsResult extends Result{
    /** An array of Persons to return to the user. */
    private ArrayList<PersonModel> data;

    public GetPersonsResult(ArrayList<PersonModel> data) {
        this.data = data;
        success = true;
    }
    public GetPersonsResult(String message) { super(message); }

    public ArrayList<PersonModel> getPersons() { return data; }

    public void setPersons(ArrayList<PersonModel> data) { this.data = data; }
}
