import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

public class RegistroServer {

    static Logger logger = Logger.getLogger("global");
    public static void main (String[] args) {
        HashMap<String,RecordRegistro> hash = new HashMap<String,RecordRegistro>();
        Socket socket = null;
        System.out.println("In attesa di connessioni...");
        try {
            ServerSocket serversocket = new ServerSocket(7000);
            while (true) {
                socket = serversocket.accept();
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                RecordRegistro record = (RecordRegistro) inStream.readObject();
                if (record.getIndirizzo()!=null) {
                    logger.info("Inserisco: "+record.getNome() + " " + record.getIndirizzo());
                     hash.put(record.getNome(), record);
                } else {
                    logger.info("Cerco: "+record.getNome());
                    RecordRegistro res = hash.get(record.getNome());
                    ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                    outStream.writeObject(res);
                    outStream.flush();
                }
            }
        } catch (EOFException e) {
            logger.severe("Problemi di connessione : "+e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            logger.severe("Lanciata Throwable : "+t.getMessage());
            t.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }
}