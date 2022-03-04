package Handlers;
import java.io.*;
import java.net.*;
import Results.ClearResult;
import Services.Clear;
import com.sun.net.httpserver.*;

public class ClearHandler extends Handler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        ClearResult result = !exchange.getRequestMethod().equalsIgnoreCase("post") ?
                            new ClearResult("Request must be a POST request") : new Clear().clear();
        writeResult(exchange, result);
    }
}
