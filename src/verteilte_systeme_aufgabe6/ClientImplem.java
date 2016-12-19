/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe6;
import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author Mike
 */
public class ClientImplem extends UnicastRemoteObject implements ClientInterface { 

    private String name;
    
    public ClientImplem(String name) throws RemoteException {
        this.name = name;
    }
    
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void print(String msg) throws RemoteException {
        System.out.println(msg);
    }
    
}
