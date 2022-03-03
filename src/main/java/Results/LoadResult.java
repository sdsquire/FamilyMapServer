package Results;

/** Indicates the result of the user's request to load the database with new data. */
public class LoadResult extends Result {
    public LoadResult(String message, boolean success) {
        super(message, success);
    }
    public LoadResult(String message) {
        super(message);
    }
}
