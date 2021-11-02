import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class Client {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        Integer primo,secondo,somma_ricevuta;


        ObjectOutputStream sock_out;
        ObjectInputStream sock_in;

        Socket socket = new Socket("localhost",9000);
        sock_out = new ObjectOutputStream(socket.getOutputStream());
        sock_in = new ObjectInputStream(socket.getInputStream());
        try {
            while (true) {
                System.out.println("Inserisci primo intero: ");
                primo = scanner.nextInt();
    
                System.out.println("Inserisci secondo intero: ");
                secondo = scanner.nextInt();
    
                sock_out.writeObject(primo);
                sock_out.flush();
                sock_out.writeObject(secondo);
                sock_out.flush();
                
                somma_ricevuta = (Integer) sock_in.readObject();
                System.out.println("Somma dal server: "+somma_ricevuta);
            }
        } finally {
            socket.close();
            scanner.close();
        }
    }
}
