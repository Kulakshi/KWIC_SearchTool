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

    private String left;
    private String keyword;
    private String right;
    private ArrayList<String> words;
    private String filename;
    private String filepath;
    private String sentenceNumber;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
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
        copy.setSentenceNumber(sentenceNumber);
        copy.setWords(words);
        return copy;
    }

}
