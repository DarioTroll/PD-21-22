import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import java.util.logging.Logger;

public class Impiegato_Stub implements Impiegato {
    static Logger logger = Logger.getLogger("global");

    public Impiegato_Stub (String host) throws Throwable {
        socket = new Socket(host,9000);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public String getNome() throws Throwable {
        out.writeObject("getNome");
        out.flush();
        return (String) in.readObject();
    }

    public String getID() throws Throwable {
        out.writeObject("getID");
        out.flush();
        return (String) in.readObject();
    }

    public int getStipendio() throws Throwable {
        out.writeObject("getStipendio");
        out.flush();
        return in.readInt();

    }

    public int aumentaStipendio(int diQuanto) throws Throwable {
        out.writeObject("aumentaStipendio");
        out.writeInt(diQuanto);
        out.flush();
        return in.readInt();   
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Chiusura fatta!");
        }
    }

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
}
