package Handlers;
import Results.FillResult;
import Services.Fill;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class FillHandler extends Handler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String[] parts = exchange.getRequestURI().toString().split("/");
        String username = parts[2];
        int generations = parts.length > 3 ? Integer.parseInt(parts[3]) : 3;

        FillResult result = (!exchange.getRequestMethod().equalsIgnoreCase("post")) ?
                            new FillResult("Must be a POST request") : new Fill(username).fill(generations);
        writeResult(exchange, result);
    }
}
