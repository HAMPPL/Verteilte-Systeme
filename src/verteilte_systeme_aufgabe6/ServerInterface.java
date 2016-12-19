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
public interface ServerInterface extends java.rmi.Remote {
    
    public boolean addClient(ClientInterface ref) throws RemoteException;
    public void removeClient(ClientInterface ref) throws RemoteException;
    public void sendMessage(String msg) throws RemoteException;
    
}
