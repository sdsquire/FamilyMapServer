package Handlers;
import Requests.LoadRequest;
import Results.LoadResult;
import Services.Load;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;

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
