import java.io.IOException;
import java.net.Socket;

/**
 * Main
 */
public class ServerMain {

    public static void main(String[] args) {
        Controller ctrl = new Controller();
        try {
            ctrl.createServerSocket(1234);
            Socket socket = ctrl.acceptConnection();
            ctrl.dataTransfer(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}