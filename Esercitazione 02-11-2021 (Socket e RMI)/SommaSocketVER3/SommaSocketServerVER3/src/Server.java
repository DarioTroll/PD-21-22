import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket;
        Socket socket;

        serverSocket = new ServerSocket(9000);
        System.out.println("In attesa di connessioni...");

        int id = 0;

        try {
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Connessione accettata per Client "+id);
                new Thread(new SommaThread(id,socket)).start();
                ++id;
            }
        } finally {
            serverSocket.close();
        }
        
    }
}
