package Handlers;
import java.io.*;
import java.net.*;

import Resources.InvalidRequestException;
import Services.Clear;
import com.sun.net.httpserver.*;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        try {
            if(!exchange.getRequestMethod().equalsIgnoreCase("post"))
                throw new InvalidRequestException("Must be a POST request");
            new Clear().clear();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } catch (InvalidRequestException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            e.printStackTrace();
        }


        exchange.close();
    }
}
