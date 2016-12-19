/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe6;

import java.rmi.RemoteException;

/**
 *
 * @author Mike
 */
public interface ClientInterface extends java.rmi.Remote {
    
    public String getName() throws RemoteException;
    public void print(String msg) throws RemoteException;
    
}
