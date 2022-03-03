package Results;

/** Indicates the result of the user's request to clear the database. */
public class ClearResult extends Result {
    public ClearResult() {
        this.message = "Clear succeeded.";
        success = true;
    }
}