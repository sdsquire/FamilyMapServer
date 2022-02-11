package DAOs;
import Models.AuthtokenModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthtokenDAO {
    private final Connection conn;
    public AuthtokenDAO(Connection conn) { this.conn = conn; }

    public void insert(AuthtokenModel authtoken) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

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
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
}

