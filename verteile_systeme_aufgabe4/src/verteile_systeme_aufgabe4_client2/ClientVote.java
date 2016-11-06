/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_client2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author mike-
 */
public class ClientVote {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        String ip = "127.0.0.1";
        int port = 4711;
        Socket socket = new Socket(ip,port);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.print("yes");
        writer.flush();
        Thread.sleep(1000);
        socket.close();
    }
     
}
