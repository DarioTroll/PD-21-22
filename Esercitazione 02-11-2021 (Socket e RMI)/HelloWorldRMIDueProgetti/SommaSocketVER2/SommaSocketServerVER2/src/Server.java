import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Integer primo,secondo,somma;
        ObjectInputStream in;
        ObjectOutputStream out;

        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("In attesa di connessioni...");
        
        Socket socket = serverSocket.accept();
        System.out.println("Connessione accettata!");
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        try {
            while (true) {
                primo = (Integer) in.readObject();
                secondo = (Integer) in.readObject();
                somma = primo+secondo;
                System.out.println("Il server ha ricevuto "+primo+" e "+secondo);
                System.out.println("Somma: "+somma);
                out.writeObject(somma);
                out.flush();
                System.out.println("Somma inviata!");
            }
        } finally {
            serverSocket.close();
        }

    }
}
