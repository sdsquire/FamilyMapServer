package DAOs;
import Models.UserModel;
import Resources.*;
import java.sql.*;

/** Intermediates between the User models and the SQL database */
public class UserDAO extends DAO{
    public UserDAO(Connection conn) {
        super(conn);
        tableName = "User";
    }
    /**
     * Inserts a new user into the database.
     * @param user A UserModel representation of the user to be inserted
     * @throws DataAccessException if there is an error accessing the data
     */
    public void insert(UserModel user) throws DataAccessException {
        String sql = "INSERT INTO User (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("CONSTRAINT_CHECK"))
                throw new DataAccessException("Improper data entered");
            else if (e.getMessage().contains("PRIMARYKEY"))
                throw new DataAccessException("User already exists");
            else if (e.getMessage().contains("NOTNULL"))
                throw new DataAccessException("Attempted to insert null for non-optional data");
            else
                throw new DataAccessException("Error accessing data");
        }
    }

    /**
     * Finds a user in the database.
     * @param username the primary key for the person
     * @return user the UserModel representation of the user searched for
     * @throws DataAccessException if there is an error accessing the data
     */
    public UserModel find(String username) throws DataAccessException {
        UserModel user;
        ResultSet rs = null;
        String sql = "SELECT * FROM User WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserModel(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user");
        } finally {
            if(rs != null)
                try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }

//    /** Drops all entries in the User database. */
//    public void Clear() throws DataAccessException {
//        String sql = "DELETE FROM User;";
//        try (Statement stmt = conn.createStatement()){ stmt.executeUpdate(sql); }
//        catch (SQLException e) {
//            e.printStackTrace();
//            throw new DataAccessException("SQL Error encountered while clearing tables");
//        }
//    }
}

