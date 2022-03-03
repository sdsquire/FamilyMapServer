package Handlers;

import Requests.RegisterRequest;
import Resources.InvalidRequestException;
import Results.RegisterResult;
import Services.Register;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("post"))
                throw new InvalidRequestException("Must be a POST request");

            Gson gson = new Gson();
            Reader requestBody = new InputStreamReader(exchange.getRequestBody());

            RegisterRequest req = gson.fromJson(requestBody, RegisterRequest.class);
            Register serv = new Register();
            RegisterResult result = serv.register(req);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
            Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
            gson.toJson(result, resBody);

            resBody.close();
//            exchange.close();
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

            exchange.close();
        }
    }
}
