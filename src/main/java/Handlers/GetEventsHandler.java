package Handlers;
import Results.GetEventResult;
import Results.GetEventsResult;
import Services.GetEvents;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class GetEventsHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] parts = exchange.getRequestURI().toString().split("/");
        String eventID = parts.length > 2 ? parts[2] : null;
        if (eventID == null) {
            GetEventsResult result = !exchange.getRequestMethod().equalsIgnoreCase("get") ? new GetEventsResult("Request must be a GET request") :
                                    !exchange.getRequestHeaders().containsKey("Authorization") ? new GetEventsResult("No authorization token to authenticate") :
                                    new GetEvents().getEvents(exchange.getRequestHeaders().getFirst("Authorization"));
            writeResult(exchange, result);
        } else {
            GetEventResult result = !exchange.getRequestMethod().equalsIgnoreCase("get") ? new GetEventResult("Request must be a GET request") :
                                    !exchange.getRequestHeaders().containsKey("Authorization") ? new GetEventResult("No authorization token to authenticate") :
                                    new GetEvents().getEvents(eventID, exchange.getRequestHeaders().getFirst("Authorization"));

            writeResult(exchange, result);
        }
    }
}
