package Results;

/** Indicates the result of a user's request to fill their family tree. */
public class FillResult extends Result {
    public FillResult(String message, boolean success) { super(message, success); }
    public FillResult(String message) { super(message); }
}
