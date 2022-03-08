package Handlers;
import Results.Result;
import Services.GetPersons;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class GetPersonsHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String[] parts = exchange.getRequestURI().toString().split("/");
        String personID = parts.length > 2 ? parts[2] : null;
        Result result = !exchange.getRequestMethod().equalsIgnoreCase("get") ? new Result("Request must be a GET request") :
                               !exchange.getRequestHeaders().containsKey("Authorization") ? new Result("No authorization token to authenticate") :
                               personID == null ? new GetPersons().getPersons(exchange.getRequestHeaders().getFirst("Authorization")) :
                                               new GetPersons().getPerson(personID, exchange.getRequestHeaders().getFirst("Authorization"));

        writeResult(exchange, result);
    }
}
