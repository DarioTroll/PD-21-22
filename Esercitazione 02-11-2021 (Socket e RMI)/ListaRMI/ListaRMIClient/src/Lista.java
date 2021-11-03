import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.ArrayList;

public interface Lista extends Remote {
	void add (String user) throws RemoteException;
	boolean delete (String user) throws RemoteException;
	ArrayList<String> list () throws RemoteException;
}
