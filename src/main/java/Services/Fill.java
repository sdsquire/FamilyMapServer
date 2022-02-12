package Services;

import Results.FillResult;

/**
 * Fills the user's family tree to a specified number of generations.
 */
public class Fill {

 /**
  * Fills the user's family tree to a specified number of generations.
  */
 public Results.FillResult Fill(String userName, int generations) {
  String message = null;
  return new FillResult(message, true);
 }
}
