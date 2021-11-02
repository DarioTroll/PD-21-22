import java.util.logging.Logger;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class App {
    static Logger logger = Logger.getLogger("global");
    public static void main(String[] args) throws Exception {
        try {

            //Cerco l'host su cui è registrato il servizio Impiegato
            RecordRegistro r = new RecordRegistro("Rossi",null);
            Socket socket = new Socket("localhost",7000);
            ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());
            sock_out.writeObject(r);
            sock_out.flush();
            ObjectInputStream sock_in = new ObjectInputStream(socket.getInputStream());
            RecordRegistro result = (RecordRegistro) sock_in.readObject();
            socket.close();
            if (result!=null) {
                Impiegato imp = new Impiegato_Stub(result.getIndirizzo());
                System.out.println("Nome: "+imp.getNome());
                System.out.println("ID: "+imp.getID());
                System.out.println("Stipendio: "+imp.getStipendio());
                System.out.println("Aumentiamo lo stipendio!");
                System.out.println("Stipendio ora: "+imp.aumentaStipendio(1000));

                ((Impiegato_Stub) imp).close();
            } else {
                System.out.println("Non esiste nessun oggetto remoto con quel nome...");
            }
        } catch (Throwable t) {
            logger.severe("Lanciata Throwable: "+t.getMessage());
            t.printStackTrace();
        }
    }
}
