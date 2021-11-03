import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Logger;

@SuppressWarnings("deprecation")
public class Server extends UnicastRemoteObject implements Lista {

	private static final long serialVersionUID = 5447915019498023125L;

	private static Logger logger = Logger.getLogger("global");
	
	ArrayList<String> listaUtenti;
	
	protected Server() throws RemoteException {
		super();
		listaUtenti = new ArrayList<String>();
	}
	

	@Override
	public void add(String user) throws RemoteException {
		logger.info("Qualcuno vuole aggiungere un utente '"+user+"'");
		listaUtenti.add(user);
	}

	@Override
	public boolean delete(String user) throws RemoteException {
		logger.info("Qualcuno vuole rimuovere l'utente '"+user+"'");
		return listaUtenti.remove(user);
	}

	@Override
	public ArrayList<String> list() throws RemoteException {
		logger.info("Qualcuno ha richiesto di prelevare la lista");
		return listaUtenti;
	}
	
	
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			logger.info("Creo l'oggetto remoto");
			Server obj = new Server();
			logger.info("Ora ne effettuo il rebind");
			Naming.rebind("ListaRemota", obj);
			logger.info("Pronto");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
