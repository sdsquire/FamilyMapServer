package Models;

/**
 * Data Access Object modeling the structure of the SQL Event table.
 */
public class EventModel {
    /**
     * A unique identifier for the event; the primary key.
     */
    private String eventID;
    /**
     * The username associated with the person; a foreign key.
     */
    private String associatedUsername;
    /**
     * The ID of the person to whim this event belongs; a foreign key.
     */
    private String personID;
    /**
     * The latitude coordinates where the event took place.
     */
    private float latitude;
    /**
     * The longitude coordinates where the event took place.
     */
    private float longitude;
    /**
     * The country in which the event took place.
     */
    private String country;
    /**
     * The city in which the event took place.
     */
    private String city;
    /**
     * The type of event that took place.
     */
    private String eventType;
    /**
     * The year the event took place.
     */
    private int year;

    public EventModel(String eventID, String username, String personID, float latitude, float longitude,
                 String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() { return eventID; }
    public String getUsername() { return associatedUsername; }
    public String getPersonID() { return personID; }
    public float getLatitude() { return latitude; }
    public float getLongitude() { return longitude; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getEventType() { return eventType; }
    public int getYear() { return year; }

    public void setEventID(String eventID) { this.eventID = eventID; }
    public void setAssociatedUsername(String associatedUsername) { this.associatedUsername = associatedUsername; }
    public void setPersonID(String personID) { this.personID = personID; }
    public void setLatitude(float latitude) { this.latitude = latitude; }
    public void setLongitude(float longitude) { this.longitude = longitude; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setYear(int year) { this.year = year; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        EventModel that = (EventModel) o;
        return eventID.equals(that.eventID) &&
                associatedUsername.equals(that.associatedUsername) &&
                personID.equals(that.personID) &&
                latitude == that.latitude &&
                longitude == that.longitude &&
                country.equals(that.country) &&
                city.equals(that.city) &&
                eventType.equals(that.eventType) &&
                year == that.year;
    }
}
