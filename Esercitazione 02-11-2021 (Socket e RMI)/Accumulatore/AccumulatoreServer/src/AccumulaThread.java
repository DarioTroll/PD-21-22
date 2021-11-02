import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AccumulaThread implements Runnable {
    
    private Accumulatore acc;
    private int ID;
    private int quanto;
    private Socket socket;
    private ObjectInputStream sock_in;
    private ObjectOutputStream sock_out;

    public AccumulaThread (Socket s, Accumulatore acc, int id) {
        this.socket=s;
        this.ID=id;
        this.acc=acc;
    }
    
    public void run () {
        System.out.println("CLIENT "+ID+" >> Eccoci!");
        synchronized(acc) {
            try {
                sock_in = new ObjectInputStream(socket.getInputStream());
                System.out.println("OIS creato");
                quanto = sock_in.readInt();
                System.out.println("Quanto letto");
                System.out.println("CLIENT "+ID+" >> Vuole aumentare di "+quanto+" l accumulatore che ora vale "+acc.getAttuale());
                acc.accumula(quanto);
                System.out.println("CLIENT "+ID+" >> Accumulatore ora vale: "+acc.getAttuale());
                sock_out = new ObjectOutputStream(socket.getOutputStream());
                sock_out.writeInt(acc.getAttuale());
                sock_out.flush();
            } catch (Exception e) {
                System.out.println("CLIENT "+ID+" >> Qualcosa è andato storto...");
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
