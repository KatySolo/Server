package server;

import executor.Md5Executor;
import tasks.ThreadedTask;
import worker.FileWorker;

import java.io.*;
import java.net.Socket;

public class ClientWorker extends ThreadedTask {

    private String executeCommand(String command) {
        final String path =  "/Users/KatySolo/IdeaProjects/FileWorker/src/test_path/animals";
        FileWorker fw = new FileWorker(path);
        fw.setRecursive(false);
        String exec_coomand = command.substring(0,4);
        switch (exec_coomand) {
            case "list":
                StringBuilder dirs = new StringBuilder();
                File file_path = new File(path);
                if (file_path.listFiles() == null) {
                    return "No files";
                } else {
                    for (File i : file_path.listFiles()) {
                        if (!i.getName().endsWith(".DS_Store")) {
                            dirs.append('\n').append(i.getName());
                        }
                    }
                    return dirs.toString();
                }
                // list - получить список файлов в директории, на которую смотрит FileWorker;
            case "hash":
                //2. hash <filename> - получить хэш соответствующего файла.
                String filename = command.split(" ")[1];
                fw.concretizePath(filename);
                return fw.execute(new Md5Executor(fw));
             default:
                 break;
        }
        return null;
    }
        private Socket myClientSocket;

        public ClientWorker(Socket s) {
            myClientSocket = s;
        }
        @Override
        public void run() {
            InputStreamReader in = null;
            PrintWriter out = null;
            try {
                in = new InputStreamReader(myClientSocket.getInputStream(),"UTF-8");
                out = new PrintWriter(
                        new OutputStreamWriter(myClientSocket.getOutputStream()));
                String clientCommand = null;
                int a = myClientSocket.getInputStream().available();
                char[] data = new char[100];
                StringBuilder userStr = new StringBuilder();
                while (true) {
                    if (myClientSocket.getInputStream().available() > 0) {
                        int result = in.read(data,0,data.length);
                        if (result < 0)
                            break;
                        for (char i: data){
                            if (i != '\u0000') {
                                userStr.append(i);
                            }
                        }
                        clientCommand = userStr.toString();
                        if (clientCommand.equals("quit")) {
                            break;
                        }
                        System.out.println("Client Says :" + clientCommand);
                        String resultExec = executeCommand(clientCommand);
                        out.println(resultExec); // 'server says'
                        out.flush();
                        userStr = new StringBuilder();
                    }
                    else {
                        Thread.sleep(1000);
                    }
                }
            }
            catch(IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    myClientSocket.close();
                    System.out.println("...Stopped");
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

    }