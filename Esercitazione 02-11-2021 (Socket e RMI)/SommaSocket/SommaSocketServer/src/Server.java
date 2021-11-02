import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Integer primo,secondo;

        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("In attesa di connessioni...");
        
        Socket socket = serverSocket.accept();
        System.out.println("Connessione accettata!");

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        System.out.println("InputStream creato");
        primo = (Integer) in.readObject();
        secondo = (Integer) in.readObject();
        Integer somma = primo+secondo;
        System.out.println("Il server ha ricevuto "+primo+" e "+secondo);
        System.out.println("Somma: "+somma);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(somma);
        System.out.println("Somma inviata!");

        serverSocket.close();

    }
}
