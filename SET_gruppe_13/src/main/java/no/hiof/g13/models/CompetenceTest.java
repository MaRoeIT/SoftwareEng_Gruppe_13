package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CompetenceTest {
    private int configId;
    private List<Integer> trackResult;
    private ArrayList<Question> questionList = new ArrayList<Question>();

    public CompetenceTest() {

    }

    public int getConfigId() {
        return this.configId;
    }
    public void setConfigId(int configId) {
        this.configId = configId;
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
        else {
            System.out.println("No config found, init test...");
            return false;
        }
    }

    public boolean runTest() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("We strongly reccommend that you take a little test. Press N to skip: ");
            String answer = scanner.next().toUpperCase();

            // Start eller skip test basert på brukerinput
            if(answer.equals("N")) {
                System.out.println("Skipping test");
                return false;
            }
        }

        System.out.println("Starting test...");
        return true;
    }

}
