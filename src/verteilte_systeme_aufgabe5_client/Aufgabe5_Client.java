/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe5_client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Aufgabe5_Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String host = null;
        try {
            MemInterface mem = (MemInterface) Naming.lookup("rmi://127.0.0.1:4711/mem");
            mem.put(5);
            System.out.println(mem.get());
        } catch (Exception ex) {
            Logger.getLogger(Aufgabe5_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
