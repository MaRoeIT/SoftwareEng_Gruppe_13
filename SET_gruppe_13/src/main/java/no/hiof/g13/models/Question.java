package no.hiof.g13.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Question {
    private String questionDescription;
    private int choicesId;
    private Map<String, Integer> choices = new HashMap<>();

    public Question() {
    }

    public Question(String questionDescription, int choicesId) {
        this.questionDescription = questionDescription;
        this.choicesId = choicesId;
    }

    public String getQuestionDescription() {
        return this.questionDescription;
    }
    public void setQuestionDescription(String question) {
        this.questionDescription = question;
    }
    public int getChoicesId() {
        return this.choicesId;
    }
    public void setChoicesId(int choicesId) {
        this.choicesId = choicesId;
    }
    public Map<String, Integer> getChoices() {
        return Collections.unmodifiableMap(choices);
    }
    public void setChoices(Map<String, Integer> choices) {
        this.choices.putAll(choices);
    }
}
