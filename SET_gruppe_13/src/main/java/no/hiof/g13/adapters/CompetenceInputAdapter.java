package no.hiof.g13.adapters;

import no.hiof.g13.models.Question;
import no.hiof.g13.ports.out.CompetenceInputPort;

public class CompetenceInputAdapter implements CompetenceInputPort {

    public String getUserInput() {
        return "pending input...";
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getAnswer(Question question, String input) {

        return 0;
    }

    public int getUserConfig(int recommendedConfig) {
        return 0;
    }
}
