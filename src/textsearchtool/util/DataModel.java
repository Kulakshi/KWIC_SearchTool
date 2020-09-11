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
    
    private ArrayList<ArrayList<String>> sentenceNodes;
//    private ArrayList<String> fileNames;
//    private ArrayList<String> filePaths;
//    private ArrayList<String> sentenceNumbers;
    private ArrayList<String[]> rows;
    private String[] columnNames = {"Left Window", "Keyword", "Right Window", "File Name", "Sentence Index"};
    
    public DataModel() {
        super();
        sentenceNodes = new ArrayList<ArrayList<String>>();
//        fileNames = new ArrayList<>();
//        filePaths = new ArrayList<>();
//        sentenceNumbers = new ArrayList<>();
        rows = new ArrayList<String[]>();
    } 

//    public ArrayList<String> getFileNames() {
//        return fileNames;
//    }
//
//    public ArrayList<String> getSentenceIndices() {
//        return sentenceNumbers;
//    }

    public ArrayList<ArrayList<String>> getSentenceNodes() {
        return sentenceNodes;
    }

//    public ArrayList<String> getFilePaths() {
//        return filePaths;
//    }
//
//    public ArrayList<String> getSentenceNumbers() {
//        return sentenceNumbers;
//    }
    
    
    
    public void setDataEntry(String sentence, ArrayList<String> nodes, String fileName, String filePath, String sentNumber){
        sentenceNodes.add(nodes);
//        fileNames.add(fileName);
//        filePaths.add(filePath);
//        sentenceNumbers.add(sentNumber);
        addRow(sentence, "", "", fileName, sentNumber);
    }
    
    public void setDataEntry(SentenceDataModel sentenceData){
        sentenceNodes.add(sentenceData.getWords());
//        fileNames.add(sentenceData.getFilename());
//        filePaths.add(sentenceData.getFilepath());
//        sentenceNumbers.add(sentenceData.getSentenceNumber()); 
        addRow(sentenceData.getLeft(), sentenceData.getKeyword(), sentenceData.getRight(), sentenceData.getFilename(), sentenceData.getSentenceNumber());
 
    }
    
    private void addRow(String left, String keyword, String right, String fileName,String sentNumber){
        String[] row = {left, keyword, right, fileName, sentNumber};
        rows.add(row);
    }
    
    public SentenceDataModel getDataEntry(int index){
        SentenceDataModel sentenceEntry = new SentenceDataModel();
        sentenceEntry.setFilename(rows.get(index)[3]);
//        sentenceEntry.setFilepath(filePaths.get(index));
        sentenceEntry.setSentenceNumber(rows.get(index)[4]);
        sentenceEntry.setWords(sentenceNodes.get(index));
        return sentenceEntry;
    }
    
    public int getSize(){
        return sentenceNodes.size();
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
        return columnNames.length;
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
