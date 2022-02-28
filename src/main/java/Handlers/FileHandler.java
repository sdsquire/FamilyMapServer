package Handlers;
import Resources.InvalidRequestException;
import com.sun.net.httpserver.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("get"))
                throw new InvalidRequestException("Request must be a GET request.");

            // FORMAT FILE PATH, CHECK IF FILE EXISTS //
            String urlPath = exchange.getRequestURI().toString();
            if (urlPath == null || urlPath.equals("/"))
                urlPath = "/index.html";
            urlPath = "/web" + urlPath;
            File file = new File(urlPath);
            if (!file.exists())
                throw new FileNotFoundException("No file " + urlPath + " exists.");

            // COPY FILE TO OUTPUT STREAM //
            Files.copy(file.toPath(), exchange.getResponseBody());


        } catch (InvalidRequestException | FileNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
