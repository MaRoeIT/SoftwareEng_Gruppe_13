package no.hiof.g13.ports.out;

import no.hiof.g13.models.Question;

public interface CompetenceInputPort {
    // Interface som tar imot generelle inputs fra bruker
    void displayMessage(String message);

    String getUserInput();

    int getAnswer(Question question, String input);

    int getUserConfig(int recommendedConfig);

}