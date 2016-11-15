/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_server;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author mike-
 */
public class FileHandler {
    
    String filename;
    
    public FileHandler(String filename) {
        this.filename = filename;
    }
    
    public synchronized VoteCount parseXML() {
        int yes = 0;
        int no = 0;
        int other = 0;
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            Node result = doc.getElementsByTagName("result").item(0);
            NodeList nList = result.getChildNodes();
            System.out.println("----------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                //System.out.println(node.getNodeName());
                if ("Yes".equals(node.getNodeName())) {
                    yes = Integer.valueOf(node.getTextContent().trim());
                }
                if ("No".equals(node.getNodeName())) {
                    no = Integer.valueOf(node.getTextContent().trim());
                }
                if ("Other".equals(node.getNodeName())) {
                    other = Integer.valueOf(node.getTextContent().trim());
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult resultFile = new StreamResult(new File(filename));
            transformer.transform(source, resultFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new VoteCount(yes, no, other);
    }
    
    public synchronized void increaseVoteCount(String nodeName) {
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Node result = doc.getElementsByTagName("result").item(0);
            NodeList nList = result.getChildNodes();
            int value = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                //System.out.println(node.getNodeName());
                if (nodeName.equals(node.getNodeName())) {
                    value = Integer.valueOf(node.getTextContent().trim());
                    System.out.println(value);
                    node.setTextContent(String.valueOf(value + 1));
                    System.out.println(node.getTextContent().trim());
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult resultFile = new StreamResult(new File(filename));
            transformer.transform(source, resultFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
