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
        /* try{
            repeatPrinting();
        }catch(Exception ex) {
            ex.printStackTrace();
        }*/
        Thread t = new Thread() {
            public void run() {
                while(true) {
                    try {
                        sendMessage("asd","asd");
                        Thread.sleep(5000);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ServerImplem.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerImplem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
    }
    
    public synchronized void repeatPrinting() throws RemoteException, InterruptedException {
        while(true) {
            sendMessage("fick","dich");
            Thread.sleep(5000);
        }
    }

    @Override
    public synchronized boolean addClient(ClientInterface ref) throws RemoteException {
        String name = ref.getName();
        System.out.println(name);
        for(Iterator<ClientInterface> iter = allClients.iterator();iter.hasNext();) {
            ClientInterface client = iter.next();
            try {
                if(client.getName().equals(name))
                    return false;
            }catch(RemoteException ex) {
                iter.remove();
            }
            
        }
        allClients.add(ref);
        return true;
    }

    @Override
    public synchronized void removeClient(ClientInterface ref) throws RemoteException {
        allClients.remove(ref);
    }

    @Override
    public synchronized void sendMessage(String name, String msg) throws RemoteException {
        Random random = new Random();
        for(Iterator<ClientInterface>iter = allClients.iterator();iter.hasNext();) {
            ClientInterface client = iter.next();
            try{
                client.print(Integer.toString(random.nextInt(10)));
            }catch(RemoteException ex) {
                iter.remove();
            }
        }
    }
    
}
