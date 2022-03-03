package Handlers;

import Results.Result;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException{}

    protected void writeResult(HttpExchange exchange, Result result) throws IOException {
        if (result.isSuccess())
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        else
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        new Gson().toJson(result, resBody);
        resBody.close();
    }


}
