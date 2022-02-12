package Services;
import Models.EventModel;
import java.util.ArrayList;

/**
 * Gets the specified event, or all events if none is specified.
 */
public class GetEvent {
 /**
  * Gets the events if eventID is specified.
  * @return
  */
 public ArrayList<EventModel> GetEvent() {
  return new ArrayList<EventModel>();
 }
 /**
  * Gets the specified event if eventID is specified;
  */
 public EventModel GetEvent(String eventID) {
  return null;
 }
}
