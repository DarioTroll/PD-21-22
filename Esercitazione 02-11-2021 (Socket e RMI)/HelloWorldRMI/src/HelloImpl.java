import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

@SuppressWarnings("deprecation")
public class HelloImpl extends UnicastRemoteObject implements Hello {
	
	
	private static final long serialVersionUID = -4469091140865645865L;
	
	static Logger logger = Logger.getLogger("global");
	
	public HelloImpl () throws RemoteException {
		
	}

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			logger.info("Creo l'oggetto remoto");
			HelloImpl obj = new HelloImpl();
			logger.info("... ora ne effettuo il rebind... ");
			Naming.rebind("HelloServer", obj);
			logger.info("Pronto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String dimmiQualcosa(String daChi) throws RemoteException {
		logger.info("Sto salutando "+daChi);
		return "Ciao";
	}

}
