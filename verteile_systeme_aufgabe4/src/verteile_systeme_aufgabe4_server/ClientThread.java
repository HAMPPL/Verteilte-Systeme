
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike Wieder
 */
public class ClientThread extends Thread {
    
    Socket clientSocket;
    
    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void run() {
        System.out.println("Thread");
        ObjectOutputStream objectWriter = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            objectWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            
            String message = reader.readLine();
            
            switch(message) {
                case "yes":
                    //Increase Vote Count
                    System.out.println("yes");
                    break;
                case "no":
                    //Increase Vote Count
                    break;
                case "other":
                    //Increase Vote Count
                    break;
                case "result":
                    //Return VoteCount object
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                objectWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
