package no.hiof.g13.models;

import no.hiof.g13.ports.CompetenceInputPort;

import java.util.*;

public class CompetenceReview{
    private CompetenceInputPort competenceInputPort;
    private final List<Integer> trackScore = new ArrayList<Integer>();
    private final ArrayList<Question> questionsList = new ArrayList<Question>();

    public CompetenceReview() {
    }

    public CompetenceReview(CompetenceInputPort competenceInputPort) {
        this.competenceInputPort = competenceInputPort;
    }

    public List<Integer> getTrackScore() {
        return this.trackScore;
    }

    public ArrayList<Question> getQuestionsList() {
        return this.questionsList;
    }

    // Legger til spørsmål som skal stilles til bruker
    public void addQuestions(Question... question) {
        getQuestionsList().addAll(Arrays.asList(question));
    }

    public boolean initReview(String input) {
        // Start eller skip test basert på brukerinput
        if(input.equals("skip")) {
            competenceInputPort.displayMessage("Skipping test");
            return false;
        }
        runReview();
        return true;
    }

    public void runReview() {
        competenceInputPort.displayMessage("Starting test");

        for(Question questionX : getQuestionsList()) {
            int questionNum = getQuestionsList().indexOf(questionX) + 1;

            competenceInputPort.displayMessage("Question " + questionNum + ": ");
            getTrackScore().add(competenceInputPort.getAnswer(questionX, competenceInputPort.getUserInput()));

        }
    }

    public int sumScore(List<Integer> list) {
        int sum = 0;
        for(int num : list) {
            sum += num;
        }
        return sum;
    }

    public int getBestResult() {

        int maxScore = 0;

        // calculating the best possible result
        for(Question questionX : getQuestionsList()) {
            maxScore += Collections.max(questionX.getChoices().values());
        }
        return maxScore;
    }

    public int calculateResult(int userScore, int maxScore, double... thresholds) {

        double userResult = (double) userScore / maxScore * 100;
        userResult = Math.round(userResult * 100.0) / 100.0;

        if(userResult > thresholds[0]) {
            return 2;
        }
        else {
            return 1;
        }
    }

    public void setUserLevel(User currentUser, int userLevel) {
            currentUser.setUserLevel(userLevel);
    }
}
