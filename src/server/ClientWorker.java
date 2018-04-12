package server;

import tasks.ThreadedTask;
import worker.FileWorker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWorker extends ThreadedTask {

    private Socket client;

    public ClientWorker(Socket clientSocket) {
        client = clientSocket;
    }

    @Override
    public void run() {
        try {

            String command = getCommand(client.getInputStream()); // got command
            String result = executeCommand(command);
            sendResult(client.getOutputStream()); // send result
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getCommand(InputStream in) {
        Scanner s = new Scanner(in).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private String executeCommand(String command) {
        FileWorker fw = new FileWorker("some king of path???");
        switch (command.substring(0,4)) {
            case "list":
                // list - получить список файлов в директории, на которую смотрит FileWorker.
                break;
            case "hash":
                //2. hash <filename> - получить хэш соответствующего файла.
                String filename = command.split(" ")[1];
                
                break;
             default:
                 break;
        }
        return null;
    }
    private void sendResult(OutputStream out) {
    }


}