package Results;

import Models.EventModel;

import java.util.ArrayList;

/**
 * Returns the result of getting an event (or multiple events).
 */
public class GetEventResult {
    /**
     * An array of Events to return to the user.
     */
    private ArrayList<EventModel> events;
    /**
     * A single event, if the user requested one event.
     */
    private EventModel event;
    /**
     * Indicates whether the getEvent was a success.
     */
    private boolean success = true;
    /**
     * Passes a message to the user in case of error.
     */
    private String message;

    /**
     * Initializes if one event was requested.
     */
    public GetEventResult(EventModel event) {
        this.event = event;
        success = true;
    }
    /**
     * Initializes if multiple events were requested.
     */
    public GetEventResult(ArrayList<EventModel> events) {
        this.events = events;
        success = true;
    }
    /**
     * Initializes in case of error.
     */
    public GetEventResult(String message) {
        this.message = message;
        success = false;
    }

    public ArrayList<EventModel> getEvents() { return events; }
    public EventModel getEvent() { return event; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }

    public void setEvents(ArrayList<EventModel> events) { this.events = events; }
    public void setEvent(EventModel event) { this.event = event; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
}
