
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike Wieder
 */
public class Server {
    
    ServerSocket socket;
    
    public Server()  {
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket server = new ServerSocket(4711);
        Socket client = null;
        while(true) {
            client = server.accept();
            new ClientThread(client).start();    
        }
        
    }

}
