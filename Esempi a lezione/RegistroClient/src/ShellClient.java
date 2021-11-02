import java.io.*;
import java.net.*;
import java.util.logging.Logger;
public class ShellClient {
	static Logger logger = Logger.getLogger("global");
	public static void main (String[] args) {
		String host = "localhost";
		String cmd;
		in = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (!(cmd=ask("Comandi: ")).equals("quit")) {
				if (cmd.equals("inserisci")) {
					System.out.println("Inserire i dati.");
					String nome = ask("Nome: ");
					String indirizzo = ask("Indirizzo: ");
					RecordRegistro r = new RecordRegistro(nome,indirizzo);
					Socket socket = new Socket(host,7000);
					ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());
					sock_out.writeObject(r);
					sock_out.flush();
					socket.close();
				} else if (cmd.equals("cerca")) {
					System.out.println("Inserire il nome da ricercare.");
					String nome = ask("Nome: ");
					RecordRegistro r = new RecordRegistro(nome,null);
					Socket socket = new Socket(host,7000);
					ObjectOutputStream sock_out = new ObjectOutputStream(socket.getOutputStream());
					sock_out.writeObject(r);
					sock_out.flush();
					ObjectInputStream sock_in = new ObjectInputStream(socket.getInputStream());
					RecordRegistro result = (RecordRegistro) sock_in.readObject();
					if (result!=null) {
						System.out.println("Indirizzo : "+result.getIndirizzo());
					} else {
						System.out.println("Record non presente!");
					}
				} else {
					System.out.println(ERRORMSG);
				}
			}
		} catch (Throwable t) {
			logger.severe("Lanciata Throwable: "+t.getMessage());
			t.printStackTrace();
		}
		System.out.println("ciao ciaooo");
	}

	private static String ask (String prompt) throws IOException {
		System.out.print(prompt+" ");
		return (in.readLine());
	}

	static final String ERRORMSG = "What?";
	static BufferedReader in = null;
}