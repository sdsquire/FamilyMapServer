package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.GetEventsResult;
import java.sql.Connection;
import java.util.ArrayList;

public class GetEvents {
    public GetEventsResult getEvents(String eventID, String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                return new GetEventsResult("Invalid authorization token");
            String username = thisUser.getUsername();

            ArrayList<EventModel> result = new ArrayList<>();
            if (eventID == null) result.addAll(new EventDAO(conn).getUserEvents(username));
            else result.add(new EventDAO(conn).find(eventID));

            db.closeConnection(true);
            return new GetEventsResult(result);
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new GetEventsResult(e.getMessage());
        }
    }
}