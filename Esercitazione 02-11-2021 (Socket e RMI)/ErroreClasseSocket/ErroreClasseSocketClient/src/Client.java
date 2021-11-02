import java.util.Scanner;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost",9005);
        System.out.println("Socket creato.");
        
        ObjectInputStream sock_in;
        
        ObjectOutputStream sock_out;
        
        int risposta;
        int codice;

        System.out.print("Vuoi inviare un new Pippo (0) o qualcos'altro al server? (1) : ");
        risposta = scanner.nextInt();

        sock_out = new ObjectOutputStream(socket.getOutputStream());

        if (risposta==0) {
            System.out.println("Sto inviando un Pippo al server");
            sock_out.writeObject(new Pippo());
            sock_out.flush();
        } else {
            System.out.println("Sto inviando una stringa al server");
            sock_out.writeObject("Maledetto");
            sock_out.flush();
        }

        sock_in = new ObjectInputStream(socket.getInputStream());
        codice = sock_in.readInt();
        System.out.println("Ricevuto dal server: "+codice);
        
        socket.close();
        scanner.close();
    }
}
