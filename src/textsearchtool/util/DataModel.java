/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsearchtool.util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bashi
 */
public class DataModel  extends AbstractTableModel{
    
    private ArrayList<String> sentences;
    private ArrayList<ArrayList<String>> sentenceNodes;
    private ArrayList<String> fileNames;
    private ArrayList<String> filePaths;
    private ArrayList<String> sentenceNumbers;
    private ArrayList<String[]> rows;
    private String[] columnNames = {"Text", "File Name", "Sentence Index"};
    
    public DataModel() {
        super();
        sentences = new ArrayList<>();
        sentenceNodes = new ArrayList<ArrayList<String>>();
        fileNames = new ArrayList<>();
        filePaths = new ArrayList<>();
        sentenceNumbers = new ArrayList<>();
        rows = new ArrayList<String[]>();
    } 

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    public ArrayList<String> getSentenceIndices() {
        return sentenceNumbers;
    }

    public ArrayList<ArrayList<String>> getSentenceNodes() {
        return sentenceNodes;
    }

    public ArrayList<String> getFilePaths() {
        return filePaths;
    }

    public ArrayList<String> getSentenceNumbers() {
        return sentenceNumbers;
    }
    
    
    
    public void setDataEntry(String sentence, ArrayList<String> nodes, String fileName, String filePath, String sentNumber){
        sentences.add(sentence);
        sentenceNodes.add(nodes);
        fileNames.add(fileName);
        filePaths.add(filePath);
        sentenceNumbers.add(sentNumber);
        addRow(sentence, fileName, sentNumber);
    }
    
    public void setDataEntry(SentenceDataModel sentenceData){
        sentences.add(sentenceData.getSentence());
        sentenceNodes.add(sentenceData.getWords());
        fileNames.add(sentenceData.getFilename());
        filePaths.add(sentenceData.getFilepath());
        sentenceNumbers.add(sentenceData.getSentenceNumber());
        addRow(sentenceData.getSentence(), sentenceData.getFilename(), sentenceData.getSentenceNumber());
    }
    
    private void addRow(String sentence,String fileName,String sentNumber){
        String[] row = {sentence, fileName, sentNumber};
        rows.add(row);
    }
    
    public SentenceDataModel getDataEntry(int index){
        SentenceDataModel sentenceEntry = new SentenceDataModel();
        sentenceEntry.setFilename(fileNames.get(index));
        sentenceEntry.setFilepath(filePaths.get(index));
        sentenceEntry.setSentence(sentences.get(index));
        sentenceEntry.setSentenceNumber(sentenceNumbers.get(index));
        sentenceEntry.setWords(sentenceNodes.get(index));
        return sentenceEntry;
    }
    
    public int getSize(){
        return sentences.size();
    }

    public ArrayList<String[]> getRows() {
        return rows;
    }

    @Override
    public int getRowCount() {
        return getSize();    
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String [] row = rows.get(rowIndex);
        return row[columnIndex];
     }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
}
