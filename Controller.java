import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    public ServerSocket servSocket;

    public void createServerSocket(int port) throws IOException{
        servSocket = new ServerSocket(port);
    }

    public Socket acceptConnection() throws IOException{
        return servSocket.accept();
    }

    public void closeServerSocket() throws IOException{
        servSocket.close();
        System.out.println("Server socket closed!");
    }

    public void dataTransfer(Socket clientSocket) throws IOException{
        try{
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // Logic to receive vote
            out.writeUTF("Lista de candidatos (responda com o número para votar): \n1. Xande\n2. Beltras\n3. Taytônico");
            out.flush();
            String msg = in.readUTF();
            System.out.println("Message from client: " + msg);

            // Logic to confirm vote
            out.writeUTF("Deseja confirmar o voto em " + msg + "? (s/n)");
            out.flush();
            String confirm = in.readUTF();
            if(confirm.equals("s")){
                out.writeUTF("Voto confirmado!");
            } else {
                out.writeUTF("Voto cancelado!");
            }
            out.flush();

            // Close streams
            in.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Something went wrong! Address: " + clientSocket.getInetAddress().getHostAddress());
        } finally {
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        }
    }
}

