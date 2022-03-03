package Services;

import Requests.LoadRequest;
import Results.LoadResult;

/**
 * Loads the database with new data.
 */
public class Load {
 /**
  * Loads the database with new data.
  * @param req
  * @return
  */
 public LoadResult Load(LoadRequest req) {
  Clear clear = new Clear();

  String message = null;
  return new LoadResult(message, true);
 }
}
