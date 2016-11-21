/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe5_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Mike
 */
public class MemImpl extends UnicastRemoteObject implements MemInterface {
    
    private int counter = 0;
    private int range = 10;
    private int in = 0;
    private int out = 0;
    private int[] puffer = new int[10];
    
    public MemImpl() throws RemoteException {
        counter = 0;
    }

    @Override
    public void put(int val) throws RemoteException {
        if(counter != range)  {
            puffer[in] = val;
            in = (in+1)%range;
            counter ++;
        }
    }

    @Override
    public int get() throws RemoteException {
        if(counter != 0) {
            int returnVal = puffer[out];
            out = (out+1)%range;
            counter --;
            return returnVal;
        }
        else return 0;
    }
    
}
