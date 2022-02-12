package Services;

/**
 * Logs a returning user into the server
 */
public class Login {
 /**
  * Generates an authorization token and returns it to the user.
  * @return A result containing the authtoken, personID, and personID.
  */
 Results.LoginResult Login(Requests.LoginRequest req) {
  try {
   String authtoken = null;
   String personID = null;
   return new Results.LoginResult(authtoken, req.getUsername(), personID);
  } catch (Exception e) {
   String message = null;
   return new Results.LoginResult(message);
  }
 }
}
