/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_client2;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author mike-
 */
public class ClientVote {
    
    public static void main(String[] args) throws IOException {
        String ip = "127.0.0.1";
        int port = 0;
        Socket socket = new Socket(ip,port);
    }
     
}
