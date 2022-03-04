package DAOs;

import Models.EventModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Resources.*;

/** Intermediates between Event models and the SQL database */
public class EventDAO extends DAO {
    public EventDAO(Connection conn) {
        super(conn);
        tableName = "Event";
    }

    /**
     * Inserts a new event into the database.
     * @param event An EventModel representation of the event to be inserted
     * @throws DataAccessException if there is an error accessing the data
     */
    public void insert(EventModel event) throws DataAccessException {
        String sql = "INSERT INTO Event (eventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("CONSTRAINT_CHECK"))
                throw new DataAccessException("Improper data entered");
            else if (e.getMessage().contains("PRIMARYKEY"))
                throw new DataAccessException("User already exists");
            else
                throw new DataAccessException("Error accessing data");
        }
    }

    /**
     * Finds an event in the database.
     * @param eventID The primary key for the event
     * @return event the EventModel representation of the event searched for
     * @throws DataAccessException if there is an issue with the SQL database
     */
    public EventModel find(String eventID) throws DataAccessException {
        EventModel event;
        ResultSet rs = null;
        String sql = "SELECT * FROM Event WHERE eventID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new EventModel(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event");
        } finally {
            if(rs != null)
                try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }
}
