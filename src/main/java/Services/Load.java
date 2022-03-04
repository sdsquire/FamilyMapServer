package Services;
import DAOs.*;
import Requests.LoadRequest;
import Results.LoadResult;
import Resources.*;
import java.sql.Connection;
import Models.*;

/** Loads the database with new data. */
public class Load {
    /**
    * Loads the database with new data.
    * @param req
    * @return
    */
    public LoadResult load(LoadRequest req) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            new Clear().clear(conn);

            // INSERT DATA FROM REQUEST //
            UserDAO uDAO = new UserDAO(conn);
            for (UserModel user : req.getUsers())
                uDAO.insert(user);
            PersonDAO pDAO = new PersonDAO(conn);
            for (PersonModel person : req.getPersons())
                pDAO.insert(person);
            EventDAO eDAO = new EventDAO(conn);
            for (EventModel event : req.getEvents())
                eDAO.insert(event);

            db.closeConnection(true);
            return new LoadResult("Successfully added " + req.getUsers().size() + " users, " +
                                req.getPersons().size() + " persons, and " +
                                req.getEvents().size() + " events to the database.", true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            return new LoadResult(e.getMessage());
        }
    }
}
