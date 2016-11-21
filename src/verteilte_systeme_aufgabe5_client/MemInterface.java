/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe5_client;

import java.rmi.*;

/**
 *
 * @author Mike
 */
public interface MemInterface extends Remote {
    public void put(int val) throws RemoteException;
    public int get() throws RemoteException;
}
