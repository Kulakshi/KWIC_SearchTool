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
public class searchHandler {
    public DataModel searchQuery(String query, DataModel dataModel, int windowSize) {
        int hits = 0;
        DataModel outputModel = new DataModel();
//        System.out.println("Searching");
        for (int i = 0 ; i < dataModel.getSize(); i++) {
            SentenceDataModel sentenceData = dataModel.getDataEntry(i);
            String sentence = sentenceData.getSentence();
            int index = -1;
            for (String word : sentenceData.getWords()) {
                index++;
                if (word.trim().equalsIgnoreCase(query)) {
                    hits++;
                    String left = "";
                    int startIndex = 0;
                    if(index > windowSize){
                        startIndex = index - windowSize;
                    }
                    left = joinWords(sentenceData.getWords(), startIndex, index);
                    if(left == null){left = "";}
                    
                    
                    String right = "";
                    int endIndex = index + 1 + windowSize;
                    if(sentenceData.getWords().size() <  endIndex){
                        endIndex = sentenceData.getWords().size();
                    }
                    
                    right = joinWords(sentenceData.getWords(), index + 1, endIndex);
                    System.out.println(right);
                    if(right == null){right = "";}
                    
                    String sentenceWindow = left + " " + word + " " + right;
                    SentenceDataModel newSentenceData = sentenceData.getCopy();
                    newSentenceData.setSentence(sentenceWindow);
                    
                    outputModel.setDataEntry(newSentenceData);
//                    System.out.println(sentence);
//                    System.out.println(sentenceData.getFilename());
//                    System.out.println(sentenceData.getSentenceNumber());
                }
            }
        }
        System.out.println(hits);
        return outputModel;
    }
    
    private String joinWords(ArrayList<String> words, int startIndex, int endIndex){
        String sent = null;
        if(words != null && words.size()>0){
            if(startIndex < words.size() && words.size() >= endIndex){
                sent = "";
                for(int i = startIndex; i < endIndex ; i++){
                    sent += words.get(i);
                    if(i+1<endIndex){
                        sent += " ";
                    }
                }
            }
        }
        return sent;
    }

}
