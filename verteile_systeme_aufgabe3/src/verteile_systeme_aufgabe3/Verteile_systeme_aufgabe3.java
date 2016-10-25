/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike-
 */
public class Verteile_systeme_aufgabe3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        int n = 10;
        int k = 5;
        RecursiveThread uno = new RecursiveThread(n, k);
        uno.join();
        System.out.println(uno.answer);
        
    }
    
}


class RecursiveThread extends Thread {
    
    int n;
    int k;
    long answer;
    
    public RecursiveThread(int n, int k) {
        this.n = n;
        this.k = k;
        start();
    }
    
    public void run() {
        if(k == 0)  {
            answer = 1;
        } else {
            RecursiveThread recThread = new RecursiveThread(n-1, k-1);
            try {
                recThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(RecursiveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            answer = (long)((double)n/k * recThread.answer);
        }
            
        
    }
    
}
