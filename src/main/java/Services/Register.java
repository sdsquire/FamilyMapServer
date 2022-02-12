package Services;

/**
 * Registers a new user.
 */
public class Register {
 /**
  * Registers a new user.
  * @return A RegisterResult containing an authtoken, username, and personID.
  */
 public Results.RegisterResult Register(Requests.RegisterRequest req) {
  String authtoken = null;
  String personID = null;
  try {
   return new Results.RegisterResult(authtoken, req.getUsername(), personID);
  } catch (Exception e) {
   String message = null;
   return new Results.RegisterResult(message);
  }
 }
}
