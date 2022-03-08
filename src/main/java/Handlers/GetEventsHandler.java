package Handlers;
import Results.Result;
import Services.GetEvents;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class GetEventsHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] parts = exchange.getRequestURI().toString().split("/");
        String eventID = parts.length > 2 ? parts[2] : null;
        Result result = !exchange.getRequestMethod().equalsIgnoreCase("get") ? new Result("Request must be a GET request") :
                        !exchange.getRequestHeaders().containsKey("Authorization") ? new Result("No authorization token to authenticate") :
                        eventID == null ? new GetEvents().getEvents(exchange.getRequestHeaders().getFirst("Authorization")) :
                        new GetEvents().getEvents(eventID, exchange.getRequestHeaders().getFirst("Authorization"));

        writeResult(exchange, result);
    }
}
