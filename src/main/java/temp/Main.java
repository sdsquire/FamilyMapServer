package temp;

import DAOs.DataAccessException;
import DAOs.Database;
import DAOs.EventDAO;
import Models.EventModel;

public class Main {
    public static void main (String [] args) throws DataAccessException {
        Database db = new Database();
        try { db.openConnection(); }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            EventDAO eventDao = new EventDAO(db.getConnection());
            eventDao.insert(new EventModel("3505", "sdsqures", "1234", 260198, 300999, "USA", "Provo", "Marriage", 2020));

//                    eventDao.insert(new EventModel("3505", "sdsqures", "1234", 260198, 300999, "USA", "Provo", "Marriage", 2020));
            System.out.println(eventDao.find("3505").getPersonID());
            //        System.out.println(eventDao.find("3506").getPersonID());
            db.clearTables();
            db.closeConnection(true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            throw e;
        }
    }
}