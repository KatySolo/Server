package main;

import dispatcher.ThreadDispatcher;
import server.WebServer;
import worker.FileWorker;

public class Main {
    public static void main(String[] args) {
        WebServer server = new WebServer(333);
        server.createSocket();
        server.startServer();
//        FileWorker fw = new FileWorker("here is a path");
//        ThreadDispatcher td = new ThreadDispatcher();
    }
}
