import java.util.Scanner;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        int da_sommare;
        int somma_attuale;
        Socket socket;
        ObjectInputStream sock_in;
        ObjectOutputStream sock_out;
        Scanner scanner = new Scanner(System.in);

        socket = new Socket("localhost",9014);
        System.out.println("Socket aperto con successo");

        System.out.println("Inserisci numero da sommare: ");
        da_sommare = scanner.nextInt();

        sock_out = new ObjectOutputStream(socket.getOutputStream());
        sock_out.writeInt(da_sommare);
        sock_out.flush();
        System.out.println("Inviato al server, in attesa di risposta...");
        sock_in = new ObjectInputStream(socket.getInputStream());
        somma_attuale = sock_in.readInt();
        System.out.println("Il server ha risposto!");
        System.out.println("Somma attuale: "+somma_attuale);
        socket.close();
        scanner.close();

    }
}
