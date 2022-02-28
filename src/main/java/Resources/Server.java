package Resources;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import Handlers.*;

/*
	When the server runs, all command-line arguments are passed in to Server.main.
	For this server, the only command-line argument is the port number on which 
		the server should accept incoming client connections.
*/
public class Server {
	private static final int MAX_WAITING_CONNECTIONS = 12;
	private HttpServer server;

	private void run(String portNumber) {
		System.out.println("Initializing HTTP Server");
		try {
			// Create a new HttpServer object.
			// Rather than calling "new" directly, we instead create
			// the object by calling the HttpServer.create static factory method.
			// Just like "new", this method returns a reference to the new object.
			server = HttpServer.create( new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		server.setExecutor(null);

		System.out.println("Creating contexts");
//		server.createContext("/clear", new ClearHandler());
//		server.createContext("/fill", new FillHandler());
//		server.createContext("/event", new GetEventHandler());
//		server.createContext("/person", new GetPersonHandler());
//		server.createContext("/load", new LoadHandler());
//		server.createContext("/login", new LoginHandler());
//		server.createContext("/register", new RegisterHandler());
		// Create and install the "default" (or "file") HTTP handler.
		// All requests that do not match the other handler URLs
		// will be passed to this handle.
		// These are requests to download a file from the server 
		// (e.g., web site files)
		server.createContext("/", new FileHandler());
		
		// Log message indicating that the HttpServer is about to start accepting incoming client connections.
		System.out.println("Starting server");
		
		// Tells the HttpServer to start accepting incoming client connections.
		// This method call will return immediately, and the "main" method
		// for the program will also complete.
		// Even though the "main" method has completed, the program will continue
		// running because the HttpServer object we created is still running
		// in the background.
		server.start();
		System.out.println("Server started");
	}

	// "main" method for the server program
	// "args" should contain one command-line argument, which is the port number
	// on which the server should accept incoming client connections.
	public static void main(String[] args) {		
		String portNumber = args[0];
		new Server().run(portNumber);
	}
}

