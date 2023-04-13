import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server listening on port 8000");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            // Create a temporary directory
            TempDirExample tempDirExample = new TempDirExample();
            tempDirExample.createTempDirectory();

            // Create a new file within the temporary directory
            String message = "Hello, World!";
            byte[] bytes = message.getBytes();
            OutputStream os = t.getResponseBody();
            t.sendResponseHeaders(200, bytes.length);
            os.write(bytes);
            os.close();

            // Delete the temporary directory and its contents
            tempDirExample.deleteTempDirectory();

        }
    }
}

