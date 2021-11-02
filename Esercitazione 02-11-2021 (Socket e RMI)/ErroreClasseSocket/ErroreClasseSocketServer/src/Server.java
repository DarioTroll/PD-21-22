import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Server {
    public static void main(String[] args) throws Exception {
        
        ServerSocket serverSocket = new ServerSocket(9005);
        System.out.println("In attesa di connessioni...");
        Socket socket = serverSocket.accept();
        System.out.println("Connessione accettata...");

        ObjectInputStream sock_in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());

        int codice_stato;

        try {
            Pippo ricevuto = (Pippo) sock_in.readObject();
            System.out.println("Ho ricevuto un Pippo, tutto ok");
            codice_stato = 0;
            sock_out.writeInt(codice_stato);
            sock_out.flush();
        } catch (ClassCastException e) {
            System.out.println("Ma cosa fai, questo non è un Pippo");
            codice_stato = 1;
            sock_out.writeInt(codice_stato);
            sock_out.flush();
        } finally {
            socket.close();
        }
        
        serverSocket.close();


    }
}
