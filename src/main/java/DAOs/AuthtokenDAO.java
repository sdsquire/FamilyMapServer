package DAOs;
import Models.AuthtokenModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Intermediates between Authtoken models and the SQL database. */
public class AuthtokenDAO {
    /** The connection to the database */
    private final Connection conn;
    public AuthtokenDAO(Connection conn) { this.conn = conn; }

    /**
     * Inserts a new authtoken into the database.
     * @param authtoken An AuthtokenModel representation of the authtoken to be
     * @throws DataAccessException if there is an error accessing the data
     */
    public void insert(AuthtokenModel authtoken) throws DataAccessException {
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) { throw new DataAccessException("Error encountered while inserting into the database"); }
    }

    /**
     * Finds an authtoken in the database.
     * @param authtokenID the primary key for the authtoken
     * @return authtoken the AuthtokenModel representation searched for
     * @throws DataAccessException if there was an error accessing the data
     */
    public AuthtokenModel find(String authtokenID) throws DataAccessException {
        AuthtokenModel authtoken;
        ResultSet rs = null;
        String sql = "SELECT * FROM Events WHERE authtokenID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtokenID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authtoken = new AuthtokenModel(rs.getString("authtoken"), rs.getString("username"));
                return authtoken;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding authtoken");
        } finally {
            if(rs != null)
                try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }

    /** Drops all entries in the authtoken database. */
    public void Clear() throws DataAccessException {
        String sql = "DELETE FROM Authtoken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) { stmt.executeUpdate(); }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }

    }
}

