package Results;
import Models.EventModel;
import java.util.ArrayList;

/** Returns the result of getting an event (or multiple events). */
public class GetEventResult extends Result {
    /** The username associated with the person; a foreign key. */
    private String associatedUsername;
    /** A unique identifier for the event; the primary key. */
    private String eventID;
    /** The ID of the person to whim this event belongs; a foreign key. */
    private String personID;
    /** The latitude coordinates where the event took place. */
    private float latitude;
    /** The longitude coordinates where the event took place. */
    private float longitude;
    /** The country in which the event took place. */
    private String country;
    /** The city in which the event took place. */
    private String city;
    /** The type of event that took place. */
    private String eventType;
    /** The year the event took place. */
    /** Initializes if multiple events were requested. */
    public GetEventResult(EventModel event) {
        String eventID = event.getEventID(); //FIXME : only sending longitude and latitude to success
        String associatedUsername = event.getUsername();
        String personID = event.getPersonID();
        float latitude = event.getLatitude();
        float longitude = event.getLongitude();
        String country = event.getCountry();
        String city = event.getCity();
        String eventType = event.getEventType();
        success = true;
    }
    /** Initializes in case of error. */
    public GetEventResult(String message) { super(message); }

    public String getAssociatedUsername() { return associatedUsername; }
    public String getEventID() { return eventID; }
    public String getPersonID() { return personID; }
    public float getLatitude() { return latitude; }
    public float getLongitude() { return longitude; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getEventType() { return eventType; }

    public void setAssociatedUsername(String associatedUsername) { this.associatedUsername = associatedUsername; }
    public void setEventID(String eventID) { this.eventID = eventID; }
    public void setPersonID(String personID) { this.personID = personID; }
    public void setLatitude(float latitude) { this.latitude = latitude; }
    public void setLongitude(float longitude) { this.longitude = longitude; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}