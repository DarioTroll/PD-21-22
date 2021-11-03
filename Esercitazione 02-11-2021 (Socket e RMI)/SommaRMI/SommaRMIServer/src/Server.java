import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

@SuppressWarnings("deprecation")
public class Server extends UnicastRemoteObject implements Somma {
	
	private static Logger logger = Logger.getLogger("global");

	private static final long serialVersionUID = -5708965005059535360L;

	protected Server() throws RemoteException {
		super();
		this.totale=0;
	}

	@Override
	public void accumula(int quanto) throws RemoteException {
		logger.info("Qualcuno ha invocato 'accumula'");
		if (quanto>0)
			totale+=quanto;
	}

	@Override
	public int getTotale() throws RemoteException {
		logger.info("Qualcuno ha invocato 'getTotale'");
		return totale;
	}
	
	private int totale;
	
	
	public static void main (String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			logger.info("Creo l'oggetto remoto");
			Server obj = new Server();
			logger.info("Ora ne effettuo il rebind");
			Naming.rebind("Accumulatore", obj);
			logger.info("Pronto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
