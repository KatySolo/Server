package main;
import server.WebServer;

public class Main {
    public static void main(String[] args) {
        WebServer server = new WebServer(8005);
        server.createSocket();
        server.startServer();
    }
}
