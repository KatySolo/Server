package server;

import client.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private int port;
    private ServerSocket serverSocket;

    public WebServer(int serverPort) {
        port = serverPort;
    }

    public void createSocket () {
        try {
           serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startServer() {
        Socket client;
        try {
            while (true) {
               client = serverSocket.accept();
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//    Это основы, с которыми разобраться будет в общем случае несложно. Теперь несложно заметить, что построенный таким образом
//        сервер является однопоточным - пока не будет вызван метод accept() - входящие соединения не будут приходить. Выходом из
//        этой ситуации будет следующее:
//        1. Поместить метод accept()  внутрь бесконечного цикла.
//        2. Как только метод разблокируется, поместить клиентский сокет в другой поток, где будет обработан запрос, и благодаря
//        пункту 1, сразу же перейти на новую итерацию цикла и снова вызвать метод accept()
