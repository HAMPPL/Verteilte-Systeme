package vs_6_server;
import java.rmi.Remote;
import java.rmi.RemoteException;

import vs_6_client.*;


public interface ServerDefinition extends Remote {
	
	public boolean addClient(ClientDefinition client) throws RemoteException;
	public boolean removeClient(ClientDefinition client) throws RemoteException;

}
