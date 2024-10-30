package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CompetenceReview {
    private int configId;
    private ArrayList<Integer> trackScore = new ArrayList<Integer>();
    private ArrayList<Question> questionList = new ArrayList<Question>();

    public CompetenceReview() {

    }

    public int getConfigId() {
        return this.configId;
    }
    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public ArrayList<Integer> getTrackScore() {
        return this.trackScore;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    // Legger til spørsmål som skal stilles til bruker
    public void addQuestions(Question... questions) {
        questionList.addAll(Arrays.asList(questions));
    }

    // Sjekker om bruker har an aktiv config for UI oppsett
    public boolean checkConfig(User currentUser) {
        if(currentUser.getConfigId() > 0) {
            System.out.println("Config found, continue login...");
            return true;
        }
        System.out.println("No config found, init test...");

        return false;
    }

    public boolean initReview() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("We strongly reccommend that you take a test. Press N to skip: ");
            String answer = scanner.next().toUpperCase();

            // Start eller skip test basert på brukerinput
            if(answer.equals("N")) {
                System.out.println("Skipping test");
                return false;
            }

            runReview();
            return true;
        }
    }

    public void runReview() {
        System.out.println("Starting test...");

        for(Question questionX : this.getQuestionList()) {
            if(questionX.getQuestionType().equals("singleChoice")) {
                getTrackScore().add(singleChoice());
            }
            else if(questionX.getQuestionType().equals("numRange")) {
                getTrackScore().add(numChoice());
            }
        }
    }

    public int sumScore() {
        int sum = 0;
        for(int num : this.getTrackScore()) {
            sum += num;
        }
        return sum;
    }

    public int calculateResult() {
        int score = sumScore();

        if(score > 20) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public int singleChoice(){
        return 1;
    }
    public int numChoice() {
        return 2;
    }

}
