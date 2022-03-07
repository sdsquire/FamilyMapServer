package Services;
import DAOs.*;
import Models.*;
import Resources.*;
import Results.FillResult;
import com.google.gson.Gson;
import java.io.*;
import java.sql.Connection;
import java.util.*;

/** Fills the user's family tree to a specified number of generations. */
public class Fill {
    private final String[] FNAMES;
    private final String[] MNAMES;
    private final String[] SNAMES;
    private final EventModel[] LOCATIONS;

    Connection conn;
    String username;
    PersonDAO pDAO;
    EventDAO eDAO;
    int peopleCount = 0;
    int eventCount = 0;

    Random rand = new Random();
    private static class nameData { public String[] data; }
    private static class locData { public EventModel[] data; }

    public Fill(String username) throws IOException { this(username, null); }
    public Fill(String username, Connection conn) throws IOException {
        this.conn = conn;
        this.username = username;
        Gson gson = new Gson();
        FNAMES = gson.fromJson(new FileReader("json/fnames.json"), nameData.class).data;
        MNAMES = gson.fromJson(new FileReader("json/mnames.json"), nameData.class).data;
        SNAMES = gson.fromJson(new FileReader("json/snames.json"), nameData.class).data;
        LOCATIONS = gson.fromJson(new FileReader("json/locations.json"), locData.class).data;
    }

    /** Fills the user's family tree to a specified number of generations; called by the handler */
    public FillResult fill(int generations) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
            System.out.println("Opening connection: Fill");

            this.fill(generations, conn);

            db.closeConnection(true);
            System.out.println("Closing connection: Fill");
            return new FillResult("Successfully added " + peopleCount + " persons and " + eventCount + " events to the database.", true);
        } catch (DataAccessException | InvalidRequestException e) {
            db.closeConnection(false);
            System.out.println("Closing connection: Fill");
            return new FillResult(e.getMessage());
        }
    }

    /** Fills the user's family tree to a specified number of generations; called by other services */
    protected void fill(int generations, Connection conn) throws DataAccessException, InvalidRequestException {
        UserDAO uDAO = new UserDAO(conn);
        pDAO = new PersonDAO(conn);
        eDAO = new EventDAO(conn);
        // LOCATE USER AND ASSOCIATED PERSON //
        UserModel targetUser = uDAO.find(username);
        if (targetUser == null)
            throw new InvalidRequestException("User \"" + username + "\" not found in database");
        PersonModel targetPerson = new PersonDAO(conn).find(targetUser.getPersonID());
        // REMOVE ALL CASES OF TARGET PERSON //
        uDAO.Remove("username", username);
        pDAO.Remove("associatedUsername", username);
        eDAO.Remove("associatedUsername", username);
        // CREATE BIRTH EVENT FOR USER //
        EventModel birth = CreateEventBase();
        birth.setEventType("birth");
        birth.setPersonID(targetPerson.getPersonID());
        int CURR_YEAR = 2022;
        birth.setYear(CURR_YEAR - 100 + rand.nextInt(100));
        eDAO.insert(birth);
        eventCount++;
        // RECURSIVELY ADD PARENTS, ADD TO DATABASE //
        generatePerson(targetPerson, birth.getYear(), generations);
        pDAO.insert(targetPerson);
        peopleCount++;

        uDAO.insert(targetUser);
    }

    private void generatePerson(PersonModel currPerson, int childBirthYear, int generations) throws DataAccessException{
        if (generations == 0) return;
        // RECURSIVELY ADD PEOPLE //
        PersonModel mother = new PersonModel( UUID.randomUUID().toString(), username, FNAMES[new Random().nextInt(FNAMES.length)],
                SNAMES[rand.nextInt(SNAMES.length)], "f", null, null, null );
        PersonModel father = new PersonModel( UUID.randomUUID().toString(), username, MNAMES[new Random().nextInt(MNAMES.length)],
                SNAMES[rand.nextInt(SNAMES.length)], "m", null, null, null );

        // CREATE NECESSARY EVENTS FOR MOTHER AND FATHER //
        //Births
        EventModel motherBirth = CreateEventBase();
        motherBirth.setEventType("birth");
        motherBirth.setPersonID(mother.getPersonID());
        motherBirth.setYear(childBirthYear - 50 + rand.nextInt(37 + 1));
        eDAO.insert(motherBirth);
        eventCount += 1;

        EventModel fatherBirth = CreateEventBase();
        fatherBirth.setEventType("birth");
        fatherBirth.setPersonID(father.getPersonID());
        fatherBirth.setYear(childBirthYear - 120 + rand.nextInt(107 + 1));
        eDAO.insert(fatherBirth);
        eventCount += 1;

        //Marriage
        EventModel marriage = CreateEventBase();
        marriage.setEventType("marriage");
        marriage.setPersonID(mother.getPersonID());
        marriage.setYear(motherBirth.getYear() > fatherBirth.getYear() ? motherBirth.getYear()+ rand.nextInt(37 + 1) :
                    fatherBirth.getYear() + rand.nextInt(37 + 1 - fatherBirth.getYear() + motherBirth.getYear()));
        eDAO.insert(marriage);
        eventCount += 1;
        marriage.setEventID(UUID.randomUUID().toString());
        marriage.setPersonID(father.getPersonID());
        eDAO.insert(marriage);
        eventCount += 1;

        //Deaths
        EventModel motherDeath = CreateEventBase();
        motherDeath.setEventType("death");
        motherDeath.setPersonID(mother.getPersonID());
        motherDeath.setYear(motherBirth.getYear()+ rand.nextInt(motherBirth.getYear() + 121 - childBirthYear));
        eDAO.insert(motherDeath);
        eventCount += 1;

        EventModel fatherDeath = CreateEventBase();
        fatherDeath.setEventType("death");
        fatherDeath.setPersonID(father.getPersonID());
        fatherDeath.setYear(childBirthYear + rand.nextInt(fatherBirth.getYear() + 121 - childBirthYear));
        eDAO.insert(fatherDeath);
        eventCount += 1;

        // INSERT PARENTS INTO DATABASE //
        mother.setSpouseID(father.getPersonID());
        generatePerson(mother, motherBirth.getYear(), generations - 1);
        pDAO.insert(mother);
        peopleCount += 1;

        father.setSpouseID(father.getPersonID());
        generatePerson(father, fatherBirth.getYear(), generations - 1);
        pDAO.insert(father);
        peopleCount += 1;

        // FORMAT AND RETURN CURRENT PERSON //
        currPerson.setMotherID(mother.getPersonID());
        currPerson.setFatherID(father.getPersonID());
    }

    private EventModel CreateEventBase() {
        EventModel tempEvent = LOCATIONS[rand.nextInt(LOCATIONS.length)];
        tempEvent.setEventID(UUID.randomUUID().toString());
        tempEvent.setAssociatedUsername(username);
        return tempEvent;
    }
}
