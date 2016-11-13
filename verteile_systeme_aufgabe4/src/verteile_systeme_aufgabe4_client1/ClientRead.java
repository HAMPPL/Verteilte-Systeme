/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_client1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import verteile_systeme_aufgabe4_server.VoteCount;

/**
 *
 * @author mike-
 */
public class ClientRead {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        String ip = "127.0.0.1";
        int port = 4711;
        Socket socket = new Socket(ip,port);
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        writer.writeObject("no");
        writer.flush();
        System.out.println("anfrage isch raus");
        Thread.sleep(1500);
        System.out.println("lese object");
        VoteCount count = (VoteCount)reader.readObject();
        System.out.println(count);
        System.out.println(count.no + " " + count.other + " " + count.yes);
        Thread.sleep(1000);
        socket.close();
    }
}
