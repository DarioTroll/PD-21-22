import java.rmi.Naming;
import java.util.Scanner;
import java.util.logging.Logger;


public class Client {
	
	private static Logger logger = Logger.getLogger("global");
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main (String[] args) {
		try {
			logger.info("Sto cercando l'oggetto remoto");
			Somma obj = (Somma) Naming.lookup("rmi://localhost/Accumulatore");
			logger.info("Trovato, ora invoco il metodo");
			System.out.print("Inserisci numero da accumulare: ");
			int quanto = scanner.nextInt();
			obj.accumula(quanto);
			System.out.println("L'accumulatore ora ammonta a : "+obj.getTotale());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
