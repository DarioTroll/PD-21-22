import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Somma extends Remote {
	void accumula (int quanto) throws RemoteException;
	int getTotale () throws RemoteException;

}
