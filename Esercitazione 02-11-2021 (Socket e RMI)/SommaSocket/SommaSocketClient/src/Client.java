import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci primo intero: ");
        Integer primo = scanner.nextInt();

        System.out.println("Inserisci secondo intero: ");
        Integer secondo = scanner.nextInt();

        Socket socket = new Socket("localhost",9000);

        ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());
        sock_out.writeObject(primo);
        sock_out.writeObject(secondo);

        ObjectInputStream sock_in = new ObjectInputStream(socket.getInputStream());
        Integer somma_ricevuta = (Integer) sock_in.readObject();
        System.out.println("Somma dal server: "+somma_ricevuta);

        socket.close();
        scanner.close();

    }
}
