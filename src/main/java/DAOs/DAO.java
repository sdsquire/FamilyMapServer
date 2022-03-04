package DAOs;

import Resources.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    protected final Connection conn;

    public DAO(Connection conn) {this.conn = conn;}
    protected String tableName = null;

    public void Clear() throws DataAccessException {
        String sql = "DELETE FROM " + tableName + ";";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing " + tableName + " table");
        }
    }

    public void Remove(String columnName, String value) throws DataAccessException {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnName + "  = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while deleting from " + tableName + "." + columnName);
        }
    }
}
