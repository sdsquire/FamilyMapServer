package Handlers;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            // FORMAT URL REQUEST, CHECKING IF FILE EXISTS //
            String urlPath = exchange.getRequestURI().toString();
            if (urlPath == null || urlPath.equals("/"))
                urlPath = "/index.html";
            urlPath = "web" + urlPath;
            File file = new File(urlPath);
            if (!file.exists()) {
                file = new File("web/HTML/404.html");
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            } else
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            // SUBMIT FILE, CLOSE CONNECTION //
            Files.copy(file.toPath(), exchange.getResponseBody());
            exchange.close();
        } catch (Exception e) {
            e.printStackTrace();
            exchange.close();
        }
    }
}
