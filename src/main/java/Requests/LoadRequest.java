package Requests;
import Models.*;
import java.util.ArrayList;

/** Passes data to be loaded into the server. */
public class LoadRequest {
    /** An array of users to load. */
    ArrayList<UserModel> users;
    /** An array of persons to load. */
    ArrayList<PersonModel> persons;
    /** An array of events to load. */
    ArrayList<EventModel> events;

    /** Initializes a load request; parameters equivalent to data members */
    public LoadRequest(ArrayList<UserModel> users, ArrayList<PersonModel> persons, ArrayList<EventModel> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public ArrayList<UserModel> getUsers() { return users; }
    public ArrayList<PersonModel> getPersons() { return persons; }
    public ArrayList<EventModel> getEvents() { return events; }

    public void setUsers(ArrayList<UserModel> users) { this.users = users; }
    public void setPersons(ArrayList<PersonModel> persons) { this.persons = persons; }
    public void setEvents(ArrayList<EventModel> events) { this.events = events; }
}
