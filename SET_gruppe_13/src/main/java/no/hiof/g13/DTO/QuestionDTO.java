package no.hiof.g13.DTO;

import no.hiof.g13.models.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDTO {
    private int questionId;
    private String questionDescription;
    private int choicesId;
    private List<String> choicesList;

    public QuestionDTO() {

    }

    public QuestionDTO(int questionId, String questionDescription, int choicesId, List<String> choicesList) {
        this.questionId = questionId;
        this.questionDescription = questionDescription;
        this.choicesId = choicesId;
        this.choicesList = choicesList;
    }

    public Question QuestionToDomain() {
        Question question = new Question(questionDescription, choicesId);

        Map<String, Integer> choicesMap = new HashMap<>();

        for(int i = 0; i < choicesList.size(); i++) {
            choicesMap.put(choicesList.get(i), i+1);
        }
        question.setChoices(choicesMap);
        return question;
    }

    public int getQuestionId() {
        return this.questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getQuestionDescription() {
        return this.questionDescription;
    }
    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }
    public int getChoicesId() {
        return this.choicesId;
    }
    public void setChoicesId(int choicesId) {
        this.choicesId = choicesId;
    }
    public List<String> getChoicesList() {
        return this.choicesList;
    }
    public void setChoicesList(List<String> choicesList) {
        this.choicesList = choicesList;
    }
}
