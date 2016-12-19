/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe6;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class ServerImplem extends UnicastRemoteObject implements ServerInterface {
    
    private ArrayList<ClientInterface> allClients;
    
    public ServerImplem() throws RemoteException {
        allClients = new ArrayList<>();
    }

    @Override
    public synchronized boolean addClient(ClientInterface ref) throws RemoteException {
        allClients.add(ref);
        return true;
    }

    @Override
    public synchronized void removeClient(ClientInterface ref) throws RemoteException {
        allClients.remove(ref);
    }

    @Override
    public synchronized void sendMessage(String msg) throws RemoteException {
        Random random = new Random();
        for(Iterator<ClientInterface>iter = allClients.iterator();iter.hasNext();) {
            ClientInterface client = iter.next();
            try{
                client.print(msg);
            }catch(RemoteException ex) {
                iter.remove();
            }
        }
    }
    
}
