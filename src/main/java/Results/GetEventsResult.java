package Results;
import Models.EventModel;
import java.util.ArrayList;

/** Returns the result of getting an event (or multiple events). */
public class GetEventsResult extends Result{
    /** An array of Events to return to the user. */
    private ArrayList<EventModel> events;
    /** A single event, if the user requested one event. */
    private EventModel event;

    /** Initializes if multiple events were requested. */
    public GetEventsResult(ArrayList<EventModel> events) {
        if (events.size() == 1)
            this.event = events.get(0);
        else
            this.events = events;
        success = true;
    }
    /** Initializes in case of error. */
    public GetEventsResult(String message) { super(message); }

    public ArrayList<EventModel> getEvents() { return events; }
    public EventModel getEvent() { return event; }

    public void setEvents(ArrayList<EventModel> events) { this.events = events; }
    public void setEvent(EventModel event) { this.event = event; }
}
