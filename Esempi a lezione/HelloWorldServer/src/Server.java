import java.util.logging.Logger;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;

public class Server {
    static Logger logger = Logger.getLogger("global");
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket serversocket = new ServerSocket(9000);
            logger.info("Socket istanziato, accetto connessioni...");
            Socket socket = serversocket.accept();
            logger.info("Connessione accettata!");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String nome = (String) in.readObject();
            logger.info("Ricevuto : "+nome);
            out.writeObject("Hello "+nome);
            socket.close();
        } catch (EOFException e) {
            logger.severe("Problema di connessione : "+e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            logger.severe("Lanciata throwable : "+t.getMessage());
            t.printStackTrace();
        }
    }
}
