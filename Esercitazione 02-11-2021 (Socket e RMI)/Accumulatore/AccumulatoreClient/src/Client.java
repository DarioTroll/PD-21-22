import java.util.Scanner;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        Integer da_sommare;
        Integer somma_attuale;
        Socket socket;
        ObjectInputStream sock_in;
        ObjectOutputStream sock_out;
        Scanner scanner = new Scanner(System.in);

        socket = new Socket("localhost",9010);
        System.out.println("Socket aperto con successo");

        System.out.println("Inserisci numero da sommare: ");
        da_sommare = scanner.nextInt();

        sock_out = new ObjectOutputStream(socket.getOutputStream());
        sock_out.writeObject(da_sommare);
        sock_in = new ObjectInputStream(socket.getInputStream());
        somma_attuale = (Integer) sock_in.readObject();
        System.out.println("Somma attuale: "+somma_attuale);
        socket.close();
        scanner.close();

    }
}
