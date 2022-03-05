package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.GetEventResult;
import Results.GetEventsResult;
import java.sql.Connection;
import java.util.ArrayList;

public class GetEvents {
    public GetEventsResult getEvents(String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                return new GetEventsResult("Invalid authorization token");
            String username = thisUser.getUsername();

            GetEventsResult result = new GetEventsResult(new EventDAO(conn).getUserEvents(username));

            db.closeConnection(true);
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new GetEventsResult(e.getMessage());
        }
    }
    public GetEventResult getEvents(String eventID, String authtoken) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            AuthtokenModel thisUser = new AuthtokenDAO(conn).find(authtoken);
            if (thisUser == null)
                return new GetEventResult("Invalid authorization token");
            String username = thisUser.getUsername();

            GetEventResult result = new GetEventResult(new EventDAO(conn).find(eventID));

            db.closeConnection(true);
            return result;
        } catch (DataAccessException e){
            db.closeConnection(false);
            return new GetEventResult(e.getMessage());
        }
    }
}