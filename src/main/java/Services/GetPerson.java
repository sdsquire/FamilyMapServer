package Services;
import Models.PersonModel;
import java.util.ArrayList;

/**
 * Gets the specified person, or all persons if none is specified.
 */
public class GetPerson {
 /**
  * Gets the persons if personID is specified.
  * @return
  */
 public ArrayList<PersonModel> GetPerson() {
  return new ArrayList<PersonModel>();
 }
 /**
  * Gets the specified person if personID is specified;
  */
 public PersonModel GetPerson(String personID) {
  return null;
 }
}
