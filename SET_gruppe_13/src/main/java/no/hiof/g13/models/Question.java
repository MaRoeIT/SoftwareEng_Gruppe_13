package no.hiof.g13.models;

import java.util.HashMap;

public class Question {
    private String questionType;
    private String questionDescription;
    private HashMap<String, Integer> textAnswers;
    private HashMap<Integer, Integer> numAnswers;

    public String getQuestionType() {
        return this.questionType;
    }

    public String getQuestionDescription() {
        return this.questionDescription;
    }
    public void setQuestionDescription(String question) {
        this.questionDescription = question;
    }
    public HashMap<String, Integer> getTextAnswers() {
        return this.textAnswers;
    }
    public HashMap<Integer, Integer> getNumAnswers() {
        return this.numAnswers;
    }
}
