package vs_6_client;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class ClientImpl extends UnicastRemoteObject implements ClientDefinition {

	String name;
	
	public ClientImpl() throws RemoteException {
		System.out.print("Namen eingeben: ");
		name = new Scanner(System.in).nextLine();
	}

	@Override
	public void inform(String msg) throws RemoteException {
            
		System.out.println("Client " + name + " erh�lt folgendes vom Server: " + msg);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.toString() == obj.toString())
			return true;
		else return false;
	}

}
