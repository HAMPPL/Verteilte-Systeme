package vs_6_server;
import vs_6_client.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerImpl extends UnicastRemoteObject implements ServerDefinition {

	ArrayList<ClientDefinition> lischd;
	
	public ServerImpl() throws RemoteException {
		lischd = new ArrayList<ClientDefinition>();
	}

	@Override
	public synchronized boolean addClient(ClientDefinition client) throws RemoteException {
		lischd.add(client);
		System.out.println("Server added client " + client.toString());
		return true;
	}

	@Override
	public synchronized boolean removeClient(ClientDefinition client) throws RemoteException {
		lischd.remove(client);
		System.out.println("Server removed client " + client.toString());
		return true;
	}
	
	public synchronized void inform(String info) throws RemoteException
	{
		for (int epps=0;epps<lischd.size();epps++)
		{
			try
			{
				lischd.get(epps).inform(info);
			}
			catch (Exception e)
			{
                                e.printStackTrace();
				System.out.println("Client not reachable:" + lischd.get(epps).toString());
			}
		}
	}

}
