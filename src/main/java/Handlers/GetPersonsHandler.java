package Handlers;
import Results.GetPersonsResult;
import Services.GetPersons;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class GetPersonsHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] parts = exchange.getRequestURI().toString().split("/");
        String personID = parts.length > 2 ? parts[2] : null;
        GetPersonsResult result = !exchange.getRequestMethod().equalsIgnoreCase("get") ? new GetPersonsResult("Request must be a GET request") :
                                !exchange.getRequestHeaders().containsKey("Authorization") ? new GetPersonsResult("No authorization token to authenticate") :
                                new GetPersons().getPersons(personID, exchange.getRequestHeaders().getFirst("Authorization"));

        writeResult(exchange, result);
    }
}
