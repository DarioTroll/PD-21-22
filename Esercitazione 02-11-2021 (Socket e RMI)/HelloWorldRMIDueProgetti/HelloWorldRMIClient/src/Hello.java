import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	String dimmiQualcosa(String daChi) throws RemoteException;
}
