package server;

import connection.ConnectionManager;
import javafx.util.Pair;
import packets.ICommandPacket;
import tasks.ThreadedTask;
import utils.*;
import utils.provider.FolderProvider;

import java.io.IOException;
import java.net.Socket;

public class ClientWorker extends ThreadedTask {

    private Socket myClientSocket;

    public ClientWorker(Socket s) {
        myClientSocket = s;
    }

    @Override
    public void run() {
        try {
            Pair<ICommandPacket, byte[]> packet = ConnectionManager.decode(myClientSocket);
            ICommand command = CommandFactory.create(packet.getKey());
            Pair<ICommandPacket, byte[]> response = command.execute(new FolderProvider(), packet.getValue());
            ConnectionManager.send(myClientSocket, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}