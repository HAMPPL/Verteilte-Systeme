/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerImplem server = new ServerImplem();
            Registry reg = LocateRegistry.createRegistry(4711);
            reg.rebind("server", server);
            while(true){
                Thread.sleep(2000);
                server.sendMessage(String.valueOf((int) (Math.random() * 100)));
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
