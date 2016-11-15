
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.ObjectInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import verteile_systeme_aufgabe4_server.FileHandler;
import verteile_systeme_aufgabe4_server.VoteCount;

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

        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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
