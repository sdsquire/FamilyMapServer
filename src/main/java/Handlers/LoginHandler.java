package Handlers;
import Requests.LoginRequest;
import Results.LoginResult;
import Services.Login;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler extends Handler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStreamReader reqBody = new InputStreamReader(exchange.getRequestBody());
        LoginRequest req = new Gson().fromJson(reqBody, LoginRequest.class);

        LoginResult result = !exchange.getRequestMethod().equalsIgnoreCase("POST") ?
                            new LoginResult("Must be post request") : new Login().login(req);
        writeResult(exchange, result);
    }
}
