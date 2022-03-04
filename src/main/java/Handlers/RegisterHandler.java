package Handlers;

import Requests.RegisterRequest;
import Results.RegisterResult;
import Services.Register;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.HttpURLConnection;

public class RegisterHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Reader requestBody = new InputStreamReader(exchange.getRequestBody());
        RegisterRequest req = new Gson().fromJson(requestBody, RegisterRequest.class);

        RegisterResult result = !exchange.getRequestMethod().equalsIgnoreCase("post") ?
                                new RegisterResult("Must be a POST request") : new Register().register(req);
        writeResult(exchange, result);
    }
}
