package vs_6_client;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientDefinition extends Remote {
	
	public void inform(String msg) throws RemoteException;

}
