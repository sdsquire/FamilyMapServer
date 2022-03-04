package DAOs;
import Models.EventModel;
import Models.PersonModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Resources.*;

/** Intermediates between Person models and the SQL database */
public class PersonDAO extends DAO {
    public PersonDAO(Connection conn) {
        super(conn);
        tableName = "Person";
    }

    /**
     * Inserts a new person into the database.
     * @param person A PersonModel representation of the person to be inserted
     * @throws DataAccessException if there is an error accessing the data
     */
    public void insert(PersonModel person) throws DataAccessException {
        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) " +
                    "VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("CONSTRAINT_CHECK"))
                throw new DataAccessException("Improper data entered");
            else if (e.getMessage().contains("PRIMARYKEY"))
                throw new DataAccessException("User already exists");
            else if (e.getMessage().contains("NOTNULL"))
                throw new DataAccessException("Cannot register user with missing data");
            else
                throw new DataAccessException("Error accessing data");
        }
    }

    /**
     * Finds a person in the database.
     * @param personID The primary key for the person
     * @return person the PersonModel representation of the person searched for
     * @throws DataAccessException if there is an error accessing that data
     */
    public PersonModel find(String personID) throws DataAccessException {
        PersonModel person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new PersonModel(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
        } finally {
            if(rs != null)
                try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }


    public ArrayList<PersonModel> getUserPersons(String username) {
        ArrayList<PersonModel> persons = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM Person WHERE associatedUsername = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next())
                persons.add(new PersonModel(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")));
            return persons;
        } catch (SQLException e) {
            e.printStackTrace();
        } //FIXME: do I need a finally block here?
        return null;
    }
}

