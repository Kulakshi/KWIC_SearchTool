/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsearchtool.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bashi
 */
public class FileReader {

    File dataFile;

    public FileReader(File file) {
        if (file.getPath().endsWith("xml")) {
            this.dataFile = file;
        } else {
            System.out.println("ERROR");
        }
    }

    public void loadFile() {
        if (dataFile != null) {

            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(dataFile);
                doc.getDocumentElement().normalize();

                NodeList sentenceTags = doc.getElementsByTagName("s");
                ArrayList sentences = new ArrayList();
                for (int i = 0; i < sentenceTags.getLength(); i++) {
                    Node nSent = sentenceTags.item(i);
                    String sentence = "";
                    if (nSent.getNodeType() == Node.ELEMENT_NODE) {
                        NodeList sentElements = nSent.getChildNodes();
                        for (int j = 0; j < sentElements.getLength(); j++) {
                            Node nWord = sentElements.item(j);
                            if (nWord.getNodeType() == Node.ELEMENT_NODE) {
                                sentence += " " + nWord.getTextContent().toString();
                            }
                        }
                    }
                    System.out.println(sentence);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
