/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe5_server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Mike-Wieder
 */
public class Aufgabe5_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MemImpl mem = new MemImpl();
            MemInterface stub = (MemInterface) UnicastRemoteObject.exportObject(mem,0);
            Registry reg = LocateRegistry.getRegistry();
            reg.bind("Mem", stub);
            System.out.println("Server l");
        } 
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
