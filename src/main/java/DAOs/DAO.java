package DAOs;

import Resources.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    /** The connection with the database */
    protected final Connection conn;
    /** Name of the table with which to interface */
    protected String tableName = null;

    public DAO(Connection conn) {this.conn = conn;}

    /** Clears all data from target table */
    public void Clear() throws DataAccessException {
        @SuppressWarnings("SqlWithoutWhere")
        String sql = "DELETE FROM " + tableName + ";";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing " + tableName + " table");
        }
    }

    /** Removes an entry from the database.
     * @param columnName the column in which to find the value
     * @param value the value to remove
     * @throws DataAccessException if there is an error deleting the value
     */
    public void Remove(String columnName, String value) throws DataAccessException {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnName + "  = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while deleting from " + tableName + "." + columnName);
        }
    }

    protected DataAccessException parseInsertException(SQLException e){
        return e.getMessage().contains("CONSTRAINT_CHECK")?  new DataAccessException("Improper data entered") :
                e.getMessage().contains("PRIMARY")? new DataAccessException("User already exists"):
                e.getMessage().contains("NOTNULL")? new DataAccessException("Attempted to insert null for non-optional data"):
                new DataAccessException("Error accessing data");
    }
}
