/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsearchtool.util;

import java.util.ArrayList;
import javax.swing.SwingWorker;
import textsearchtool.ProgressDialog;

/**
 *
 * @author Bashi
 */
public class searchHandler {

    public DataModel searchQuery(String query, DataModel dataModel, int windowSize) {
        
        DataModel outputModel = new DataModel();
        
        SwingWorker worker = new SwingWorker() {
            int hits = 0;
            
            @Override
            protected Object doInBackground() throws Exception {
                for (int i = 0; i < dataModel.getSize(); i++) {
                    SentenceDataModel sentenceData = dataModel.getDataEntry(i);
                    int index = -1;
                    for (String word : sentenceData.getWords()) {
                        index++;
                        if (word.trim().equalsIgnoreCase(query)) {
                            hits++;
                            String left = "";
                            int startIndex = 0;
                            if (index > windowSize) {
                                startIndex = index - windowSize;
                            }
                            left = joinWords(sentenceData.getWords(), startIndex, index);
                            if (left == null) {
                                left = "";
                            }

                            String right = "";
                            int endIndex = index + 1 + windowSize;
                            if (sentenceData.getWords().size() < endIndex) {
                                endIndex = sentenceData.getWords().size();
                            }

                            right = joinWords(sentenceData.getWords(), index + 1, endIndex);
                            
                            if (right == null) {
                                right = "";
                            }

                            String sentenceWindow = left + " " + word + " " + right;
                            SentenceDataModel newSentenceData = sentenceData.getCopy();
                            newSentenceData.setLeft(left);
                            newSentenceData.setRight(right);
                            newSentenceData.setKeyword(word);

                            outputModel.setDataEntry(newSentenceData);
                        }
                    }
                    
                    int progress = (int)(i * 100 /dataModel.getSize());
                    setProgress(progress);
                }
                return null;
            }

        };

            ProgressDialog.showProgress(null, worker, "Searching...", "");
        return outputModel;
    }

    private String joinWords(ArrayList<String> words, int startIndex, int endIndex) {
        String sent = null;
        if (words != null && words.size() > 0) {
            if (startIndex < words.size() && words.size() >= endIndex) {
                sent = "";
                for (int i = startIndex; i < endIndex; i++) {
                    sent += words.get(i);
                    if (i + 1 < endIndex) {
                        sent += " ";
                    }
                }
            }
        }
        return sent;
    }

}
