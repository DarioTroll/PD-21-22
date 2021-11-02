import java.util.logging.Logger;

import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;

public class Client {
    static Logger logger = Logger.getLogger("global");
    public static void main(String[] args) throws Exception {
        try {
            Socket socket = new Socket("localhost",9000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("Giovanni");
            System.out.println(in.readObject());
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
