import dispatcher.ThreadDispatcher;
import worker.FileWorker;

public class Main {
    public static void main(String[] args) {
        FileWorker fw = new FileWorker("here is a path");
        ThreadDispatcher td = new ThreadDispatcher();
    }
}
