/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilte_systeme_aufgabe1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike-
 */
public class PetriThreads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Semaphore[] semaphore = new Semaphore[20];
        for(int i = 0; i < semaphore.length; i++) {
            semaphore[i] = new Semaphore(0);
        }
        DummyThread t1 = new DummyThread("Thread1", semaphore, 1);
        DummyThread t2 = new DummyThread("Thread2", semaphore, 2);
        DummyThread t3 = new DummyThread("Thread3", semaphore, 3);
        DummyThread t4 = new DummyThread("Thread4", semaphore, 4);
        DummyThread t5 = new DummyThread("Thread5", semaphore, 5);
        DummyThread t6 = new DummyThread("Thread6", semaphore, 6);
        DummyThread t7 = new DummyThread("Thread7", semaphore, 7);
        
        
    }
        
}


class DummyThread extends Thread {

    private Semaphore[] semaphore; 
    String threadName;
    int activity;
    
    public DummyThread(String threadName, Semaphore[] semaphore, int activity) {
        this.threadName = threadName;
        this.semaphore = semaphore;
        this.activity = activity;
        start();
    }
    
    @Override
    public void run() {
        try {
        switch(activity) {
            case 1:
                System.out.println(threadName + "is running");
                semaphore[0].release();//Fuer a2
                semaphore[1].release();//Fuer a3
                
                break;
            case 2:
                semaphore[0].acquire();//Von a1
                System.out.println(threadName + "is running");
                semaphore[2].release();//Fuer a4
                semaphore[3].release();//Fuer a5
                
                break;
            case 3:
                semaphore[1].acquire();//von a1
                System.out.println(threadName + "is running");
                semaphore[4].release();//Fuer a5
                semaphore[5].release();//Fuer a6
                
                break;
            case 4:
                semaphore[2].acquire();//Von a2
                System.out.println(threadName + "is running");
                semaphore[6].release();//Fuer a7
                
                break;
            case 5:
                semaphore[3].acquire();//Von a2
                semaphore[4].acquire();//Von a3
                System.out.println(threadName + "is running");
                semaphore[7].release();//Fuer a7
                
                break;
            case 6:
                semaphore[5].acquire();//Von a3
                System.out.println(threadName + "is running");
                semaphore[8].release();//Fuer a7
                
                break;
            case 7:
                semaphore[6].acquire();
                semaphore[7].acquire();
                semaphore[8].acquire();
                System.out.println(threadName + "is running");
                break;
            
        }
        }catch(InterruptedException e) {
        }
        
    }
}
