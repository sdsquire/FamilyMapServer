package Results;
import Models.EventModel;
import java.util.ArrayList;

/** Returns the result of getting an event (or multiple events). */
public class GetEventResult extends Result{
    /** An array of Events to return to the user. */
    private ArrayList<EventModel> events;
    /** A single event, if the user requested one event. */
    private EventModel event;

    /** Initializes if one event was requested. */
    public GetEventResult(EventModel event) { this.event = event; }
    /** Initializes if multiple events were requested. */
    public GetEventResult(ArrayList<EventModel> events) { this.events = events; }
    /** Initializes in case of error. */
    public GetEventResult(String message) {
        this.message = message;
        success = false;
    }

    public ArrayList<EventModel> getEvents() { return events; }
    public EventModel getEvent() { return event; }

    public void setEvents(ArrayList<EventModel> events) { this.events = events; }
    public void setEvent(EventModel event) { this.event = event; }
}
