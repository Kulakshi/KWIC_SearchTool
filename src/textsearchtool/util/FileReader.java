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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    File dataFiles[];

    public FileReader(File[] files) {
        this.dataFiles = files;
    }

    public DataModel loadSentences() {
        DataModel dataModel = new DataModel();
        if (dataFiles != null) {
            for (File dataFile : dataFiles) {
                if (dataFile.getPath().endsWith("xml")) {
                    try {
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(dataFile);
                        doc.getDocumentElement().normalize();

                        NodeList sentenceTags = doc.getElementsByTagName("s");
                        for (int i = 0; i < sentenceTags.getLength(); i++) {
                            Node sentenceTag = sentenceTags.item(i);
                            String sentenceNumber = ((Element) sentenceTag).getAttribute("n");
                            ArrayList<String> words = new ArrayList<String>();
                            String sentence = "";
                            if (sentenceTag.getNodeType() == Node.ELEMENT_NODE) {
                                NodeList sentElements = sentenceTag.getChildNodes();
                                for (int j = 0; j < sentElements.getLength(); j++) {
                                    Node nWord = sentElements.item(j);
                                    if (nWord.getNodeType() == Node.ELEMENT_NODE) {
                                        sentence += " " + nWord.getTextContent().toString();
                                        words.add(nWord.getTextContent().toString());
                                    }
                                }
                            }
                            dataModel.setDataEntry(sentence, words, dataFile.getName(), dataFile.getAbsolutePath(), sentenceNumber);

                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "An error occurred while parsing files. Please add xml files in correct format",
                                "File Parsing Error",
                                JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Only XML files are accepted.",
                            "File Type Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        return dataModel;
    }

}
