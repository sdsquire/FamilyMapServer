package Services;
import DAOs.*;
import Resources.DataAccessException;
import Resources.Database;
import Results.ClearResult;

import java.sql.Connection;

/** Clears the database. */
public class Clear {
 /**
  * Clears the database
  * @return A ClearResult object that indicates success or failure
  */
 public ClearResult clear() {
     Database db = new Database();
     try {
         Connection conn = db.openConnection();
         new PersonDAO(conn).Clear();
         new UserDAO(conn).Clear();
         new AuthtokenDAO(conn).Clear();
         new EventDAO(conn).Clear();

         db.closeConnection(true);
         return new ClearResult();
     } catch (DataAccessException e) {
         e.printStackTrace();
         try {
             db.closeConnection(false);
         } catch (DataAccessException ex) {}
         return new ClearResult();
     }
 }
}
