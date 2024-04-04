import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        System.out.println("Stablishing connection...");
        try{
            Socket socket = new Socket("localhost", 1234); // Substituir Localhost pelo IP do servidor
            System.out.println("Connection stablished!");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Receiving list of candidates
            String msg = in.readUTF();
            System.out.println("Message from server: " + msg);

            out.writeUTF("1");
            out.flush();

            // Receiving confirmation
            String confirm = in.readUTF();
            System.out.println("Message from server: " + confirm);

            // Response to confirmation
            out.writeUTF("s");
            out.flush();

            // Receiving final message
            String finalMsg = in.readUTF();
            System.out.println("Message from server: " + finalMsg);
            
            // Close streams
            in.close();
            out.close();
            socket.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
