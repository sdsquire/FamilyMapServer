package Services;
import Models.AuthtokenModel;
import Resources.Database;
import DAOs.AuthtokenDAO;

public class VerifyAuthtoken {
    public boolean verify(String authtoken) {
        Database db = new Database();
        try {
            AuthtokenDAO AuthDAO = new AuthtokenDAO(db.openConnection());
            boolean result = AuthDAO.find(authtoken) != null;
            db.closeConnection(false);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}