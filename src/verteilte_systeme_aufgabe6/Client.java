/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Client {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerInterface server = (ServerInterface)Naming.lookup("rmi://127.0.0.1:4711/server");
            ClientImplem client = new ClientImplem("client4");
            
            if(server.addClient(client)) {
                //System.out.println("Give Input");
                while(true){}
                //sendInputToServer(server, "client1");
                //server.removeClient(client);
            } else {
                System.out.println("Name already exists");
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
    private static void sendInputToServer(ServerInterface server, String name) {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while((line = input.readLine()) != null) {
                if(line.equals("ende") || line.equals("Ende")) 
                    break;
                server.sendMessage(name, line);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
