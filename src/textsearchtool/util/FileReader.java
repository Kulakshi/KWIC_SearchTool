/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsearchtool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import textsearchtool.ProgressDialog;

/**
 *
 * @author Bashi
 */
public class FileReader {

    public DataModel loadSentences(ArrayList<File> files, DataModel dataModel) throws RuntimeException{
        ArrayList<File> dataFiles = files;
        if (dataModel == null) {
            dataModel = new DataModel();
        }

        final DataModel data_model = dataModel;

        if (dataFiles != null) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            SwingWorker worker = new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    int index = -1;
                    for (File dataFile : dataFiles) {
                        index++;
                        int progress = (int)(index * 100 /dataFiles.size());
                        setProgress(progress);
                        if (dataFile != null) {
                            if (dataFile.getPath().endsWith("xml")) {
                                try {
                                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                                    Document doc = dBuilder.parse(dataFile);
                                    doc.getDocumentElement().normalize();

                                    NodeList sentenceTags = doc.getElementsByTagName("s");
                                    for (int i = 0; i < sentenceTags.getLength(); i++) {
                                        Node sentenceTag = sentenceTags.item(i);
                                        String sentenceNumber = ((Element) sentenceTag).getAttribute("n");
                                        ArrayList<String> words = new ArrayList<String>();
                                        
                                        if (sentenceTag.getNodeType() == Node.ELEMENT_NODE) {
                                            NodeList sentElements = sentenceTag.getChildNodes();
                                            for (int j = 0; j < sentElements.getLength(); j++) {
                                                Node nWord = sentElements.item(j);
                                                if (nWord.getNodeType() == Node.ELEMENT_NODE) {
//                                                    System.out.println(nWord.getTextContent().toString());
                                                    words.add(nWord.getTextContent().toString());
                                                }
                                            }
                                        }else{
//                                            System.out.println("NOT AN ELEMENT TAG");
                                        }
                                        data_model.setDataEntry(null, words, dataFile.getName(), dataFile.getAbsolutePath(), sentenceNumber);

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
                        }else{
//                            System.out.println("NOT A DATA FILE");
                        }
                    }
                    return null;
                }

            };

            ProgressDialog.showProgress(null, worker, "Loading files...", "");

        }
        return dataModel;
    }

    public void listFilesForFolder(File folder, ArrayList<File> files) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, files);
            } else {
                files.add(fileEntry);
            }
        }
    }

}
//
//class ReadAndPrintXMLFileWithStAX {
//
//    public static void readxml(String filepath) {
//        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//        InputStream in;
//        try {
//            in = new FileInputStream(filepath);
//
//            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
//            streamReader.nextTag(); // Advance to "book" element
//            streamReader.nextTag(); // Advance to "person" element
//
//            int sents = 0;
//            while (streamReader.hasNext()) {
//                if (streamReader.isStartElement()) {
//                    if (streamReader.getLocalName() == "s") {
//                        sents++;
//                    } else {
//
//                        System.out.println(streamReader.getElementText());
//                    }
//                }
//                streamReader.next();
//            }
//            System.out.print(sents);
//            System.out.println(" persons");
//        } catch (Exception ex) {
//            Logger.getLogger(ReadAndPrintXMLFileWithStAX.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//}
