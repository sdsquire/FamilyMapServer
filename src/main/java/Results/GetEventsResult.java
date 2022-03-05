package Results;
import Models.EventModel;
import java.util.ArrayList;

/** Returns the result of getting an event (or multiple events). */
public class GetEventsResult extends Result{
    /** An array of Events to return to the user. */
    private ArrayList<EventModel> data;

    /** Initializes if multiple events were requested. */
    public GetEventsResult(ArrayList<EventModel> events) {
        this.data = events;
        success = true;
    }
    /** Initializes in case of error. */
    public GetEventsResult(String message) { super(message); }

    public ArrayList<EventModel> getData() { return data; }

    public void setData(ArrayList<EventModel> events) { this.data = events; }
}
