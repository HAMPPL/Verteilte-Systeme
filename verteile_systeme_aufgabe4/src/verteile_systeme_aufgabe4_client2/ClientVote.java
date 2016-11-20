/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_client2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 *
 * @author mike-wieder
 */
public class ClientVote {
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        String ip = "192.168.0.";
        int port = 4711;
        
        for (int i = 0; i < 10; i++) {
            
            Socket socket = new Socket(ip, port);
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject("Yes");
            writer.flush();
            Thread.sleep(1000);
            socket.close();
        }
        
        for (int i = 0; i < 5; i++) {
            Socket socket = new Socket(ip, port);
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject("No");
            writer.flush();
            Thread.sleep(1000);
            socket.close();
        }
        
        for (int i = 0; i < 5; i++) {
            Socket socket = new Socket(ip, port);
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject("Other");
            writer.flush();
            Thread.sleep(1000);
            socket.close();
        }
    }
     
}
