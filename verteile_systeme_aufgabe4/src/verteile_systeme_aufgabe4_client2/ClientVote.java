/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_client2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import verteile_systeme_aufgabe4_server.VoteCount;

/**
 *
 * @author mike-wieder
 */
public class ClientVote {
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        
        for (int i = 0; i < 10; i++) {
            String ip = "127.0.0.1";
            int port = 4711;
            Socket socket = new Socket(ip, port);
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject("Yes");
            writer.flush();
            Thread.sleep(1000);
            socket.close();
        }
        
        for (int i = 0; i < 5; i++) {
            String ip = "127.0.0.1";
            int port = 4711;
            Socket socket = new Socket(ip, port);
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject("No");
            writer.flush();
            Thread.sleep(1000);
            socket.close();
        }
        
        for (int i = 0; i < 5; i++) {
            String ip = "127.0.0.1";
            int port = 4711;
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
