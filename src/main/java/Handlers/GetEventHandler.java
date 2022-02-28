package Handlers;
import java.io.*;
import java.net.*;
import Resources.InvalidRequestException;
import Services.VerifyAuthtoken;
import com.sun.net.httpserver.*;

public class GetEventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("get"))
                throw new InvalidRequestException("Request must be a GET request.");
            Headers reqHeaders = exchange.getRequestHeaders();
            if (!reqHeaders.containsKey("Authorization"))
                throw new InvalidRequestException("Request must contain an Authtoken.");
            if (!(new VerifyAuthtoken().verify(reqHeaders.getFirst("Authorization"))))
                throw new InvalidRequestException("Authtoken not in database.");
            //TODO return event or events
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            OutputStream respBody = exchange.getResponseBody();
            OutputStreamWriter sw = new OutputStreamWriter(respBody);
            sw.write(""); //FIXME should be the json result of the events
            sw.flush();
        } catch (InvalidRequestException ire) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            e.printStackTrace();
        }
        exchange.getResponseBody().close();
    }
}
