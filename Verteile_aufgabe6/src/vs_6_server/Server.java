package vs_6_server;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class Server {

	public static void main(String[] args) {
		try {
			ServerImpl server = new ServerImpl();
			LocateRegistry.createRegistry(7825);
			Naming.rebind("//172.20.10.9:7825/Inform", server);
			System.out.println("Server initialized successfully!");
			while (true)
			{
				Thread.sleep(1000);
				int x = (int)(Math.random() * 1000); 
				server.inform(String.valueOf(x));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
