
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectInputStream;
import verteile_systeme_aufgabe4_server.FileHandler;

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
    ObjectOutputStream objectWriter = null;
    FileHandler fileHandler = null;

    public ClientThread(Socket clientSocket, FileHandler fileHandler) {
        this.clientSocket = clientSocket;
        this.fileHandler = fileHandler;
        try {
            objectWriter = new ObjectOutputStream(this.clientSocket.getOutputStream());
            objectWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(clientSocket.getInputStream());
            String message = (String) reader.readObject();
            switch (message) {
                case "Yes":
                    //Increase Vote Count
                    fileHandler.increaseVoteCount("Yes");
                    break;
                case "No":
                    fileHandler.increaseVoteCount("No");
                    break;
                case "Other":
                    fileHandler.increaseVoteCount("Other");
                    break;
                case "result":
                    objectWriter.writeObject(fileHandler.parseXML());
            }

        } catch (IOException | ClassNotFoundException ex) {
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
