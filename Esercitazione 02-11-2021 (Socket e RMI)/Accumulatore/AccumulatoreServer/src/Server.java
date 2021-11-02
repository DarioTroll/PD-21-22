import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9014);
        System.out.println("ServerSocket creato sulla porta 9014");
        Accumulatore accumulatore = new Accumulatore();
        System.out.println("Accumulatore creato");
        Socket temp_socket;
        int id = 0;
        System.out.println("In attesa di connessioni...");
        try {
            while (true) {
                temp_socket = serverSocket.accept();
                System.out.println("Connessione accettata, nuovo Client "+id);
                new Thread(new AccumulaThread(temp_socket,accumulatore, id)).start();
                ++id;
            }
        } finally {
            serverSocket.close();
        }
    }
}
