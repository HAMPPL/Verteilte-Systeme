package vs_6_client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import vs_6_server.*;

//import bin.vs_6_server.ServerDefinition;


public class Client {

	public static void main(String[] args) {
		
		try {
			ServerDefinition server = (ServerDefinition) Naming.lookup("rmi://localhost:7825/Inform");
			ClientImpl client = new ClientImpl();
			System.out.println("Anmeldeversuch von Client " + client.toString());
			server.addClient(client);
			System.out.println("Client " + client.toString() + " registriert!");
			String c = new Scanner(System.in).nextLine();
			if (c.equals("c")) server.removeClient(client);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
