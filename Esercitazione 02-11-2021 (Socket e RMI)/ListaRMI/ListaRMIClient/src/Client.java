import java.util.logging.Logger;
import java.rmi.Naming;
import java.util.Scanner;
import java.util.ArrayList;

public class Client {
	
	private static Logger logger = Logger.getLogger("global");
	private static Scanner scanner = new Scanner(System.in);
	private static String cmd;
	
	public static void main (String[] args) {
		try {
			logger.info("Recupero l'oggetto remoto");
			Lista obj = (Lista) Naming.lookup("rmi://localhost/ListaRemota");
			while (!(cmd=ask()).equals("exit")) {
				switch (cmd) {
					case "add":
						System.out.print("Inserisci un nuovo utente: ");
						obj.add(scanner.next());
						break;
					case "delete":
						System.out.print("Rimuovi un utente: ");
						if (obj.delete(scanner.next())) {
							System.out.println("Rimozione avvenuta con successo!");
						} else {
							System.out.println("Tale utente è inesistente...");
						}
						break;
					case "list":
						ArrayList<String> temp = obj.list();
						System.out.println("LISTA UTENTI");
						for (String s: temp) {
							System.out.println("Utente: "+s);
						}
						break;
					default:
						System.out.println("Non ho capito...");
						break;
				}
			}
			System.out.println("Ciao ciao!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String ask () {
		System.out.print("Operazione : ");
		return scanner.next();
	}
}
