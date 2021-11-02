import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SommaThread implements Runnable {
    private Socket socket;
    private ObjectInputStream sock_in;
    private ObjectOutputStream sock_out;
    private int ID;

    public SommaThread (int id, Socket s) throws Exception {
        this.ID=id;
        this.socket=s;
        this.sock_in = new ObjectInputStream(s.getInputStream());
        this.sock_out = new ObjectOutputStream(s.getOutputStream());
    }

    public void run () {
        Integer primo,secondo,somma;
        System.out.println("CLIENT "+ID+" >> Eccoci!");
        try {
            while (true) {
                primo = (Integer) sock_in.readObject();
                secondo = (Integer) sock_in.readObject();
                somma = primo+secondo;
                System.out.println("CLIENT "+ID+" >> Il server ha ricevuto "+primo+" e "+secondo);
                System.out.println("CLIENT "+ID+" >> Somma : "+somma);
                sock_out.writeObject(somma);
                sock_out.flush();
                System.out.println("CLIENT "+ID+" >> Somma inviata !");
            }
        } catch (Exception e) {
            System.out.println("CLIENT "+ID+" >> Qualcosa è andato storto ... ");
        } finally {
            try {
                socket.close();
            } catch (IOException exc) {
                System.out.println("CLIENT "+ID+" >> Errore chiusura socket");
            }
        }
        
    }

}
