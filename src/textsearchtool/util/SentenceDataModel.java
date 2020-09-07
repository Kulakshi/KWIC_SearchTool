/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsearchtool.util;

import java.util.ArrayList;

/**
 *
 * @author Bashi
 */
public class SentenceDataModel {

    private String sentence;
    private ArrayList<String> words;
    private String filename;
    private String filepath;
    private String sentenceNumber;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSentenceNumber() {
        return sentenceNumber;
    }

    public void setSentenceNumber(String sentenceNumber) {
        this.sentenceNumber = sentenceNumber;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public SentenceDataModel getCopy() {
        SentenceDataModel copy = new SentenceDataModel();
        copy.setFilename(filename);
        copy.setFilepath(filepath);
        copy.setSentence(sentence);
        copy.setSentenceNumber(sentenceNumber);
        copy.setWords(words);
        return copy;
    }

}
