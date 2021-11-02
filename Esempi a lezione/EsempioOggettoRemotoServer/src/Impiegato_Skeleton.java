import java.net.*;
import java.io.*;

public class Impiegato_Skeleton extends Thread {

    public Impiegato_Skeleton (ImpiegatoServer server) {
        mioServer=server;
    }

    public static void main (String[] args) {
        ImpiegatoServer impiegato = new ImpiegatoServer("Mario Rossi", "01721", 30000);
        Impiegato_Skeleton skel = new Impiegato_Skeleton(impiegato);
        skel.start();
    }

    public void run () {
        Socket socket = null;
        String metodo;
        int parametro;
        System.out.println("Attendo connessioni...");
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            socket = serverSocket.accept();
            System.out.println("Accettata una connessione... attendo comandi.");
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                metodo = (String) inStream.readObject();
                if (metodo.equals("getNome")) {
                    System.out.println("Richiesto: getNome");
                    outStream.writeObject(mioServer.getNome());
                    outStream.flush();
                } else if (metodo.equals("getID")) {
                    System.out.println("Richiesto: getID");
                    outStream.writeObject(mioServer.getID());
                    outStream.flush();
                } else if (metodo.equals("getStipendio")) {
                    System.out.println("Richiesto: getStipendio");
                    outStream.writeInt(mioServer.getStipendio());
                    outStream.flush();
                } else if (metodo.equals("aumentaStipendio")) {
                    System.out.println("Richiesto: aumentaStipendio");
                    parametro = inStream.readInt();
                    outStream.writeInt(mioServer.aumentaStipendio(parametro));
                    outStream.flush();
                } else {
                    break;
                }
            }
            serverSocket.close();
        } catch (EOFException e) {
            System.out.println("Terminata la connessione!");
        } catch (Throwable t) {
            System.out.println("Skeleton: "+t.getMessage());
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

    private ImpiegatoServer mioServer;
}
