package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private int port;

    public WebServer(int serverPort) {
        port = serverPort;
    }

    public void createSocket () {
        Socket client;
        try {
            ServerSocket server = new ServerSocket(port);
            client = server.accept();
            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
