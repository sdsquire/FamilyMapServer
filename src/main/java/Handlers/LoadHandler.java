package Handlers;

import Requests.LoadRequest;
import Requests.RegisterRequest;
import Results.LoadResult;
import Results.RegisterResult;
import Services.Load;
import Services.Register;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class LoadHandler extends Handler{
    @Override
    public void handle (HttpExchange exchange) throws IOException {
        Reader requestBody = new InputStreamReader(exchange.getRequestBody());
        LoadRequest req = new Gson().fromJson(requestBody, LoadRequest.class);

        LoadResult result = !exchange.getRequestMethod().equalsIgnoreCase("post") ?
                new LoadResult("Must be a POST request") : new Load().load(req);
        writeResult(exchange, result);
    }
}
