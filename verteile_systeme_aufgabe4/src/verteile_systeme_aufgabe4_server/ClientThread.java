
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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
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
    

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
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
            String message = (String)reader.readObject();
            switch (message) {
                case "yes":
                    //Increase Vote Count
                    parseXML("src\\verteile_systeme_aufgabe4_server\\VotingResults.xml");
                    break;
                case "no":
                    System.out.println("no");
                    objectWriter.writeObject(new VoteCount(0,5,10));
                    break;
                case "other":
                    //Increase Vote Count
                    break;
                case "result":
                //Return VoteCount object
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

    public void parseXML(String filename) {
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("result");
            System.out.println("----------------------------");
            Node rNode = nList.item(0);
            Element eElement = (Element) rNode;
            NodeList childNodes = rNode.getChildNodes();
            System.out.println(childNodes.item(0).getTextContent());
            System.out.println(childNodes.item(1).getTextContent());
            System.out.println(childNodes.item(2).getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
