package no.hiof.g13.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Question {
    private String questionType;
    private String questionDescription;
    private Map<String, Integer> choices = new HashMap<>();

    public Question() {
    }

    public Question(String questionType, String questionDescription) {
        this.questionType = questionType;
        this.questionDescription = questionDescription;
    }


    public String getQuestionType() {
        return this.questionType;
    }
    public String getQuestionDescription() {
        return this.questionDescription;
    }
    public void setQuestionDescription(String question) {
        this.questionDescription = question;
    }
    public Map<String, Integer> getChoices() {
        return Collections.unmodifiableMap(choices);
    }
    public void setChoices(Map<String, Integer> choices) {
        this.choices.putAll(choices);
    }
}
