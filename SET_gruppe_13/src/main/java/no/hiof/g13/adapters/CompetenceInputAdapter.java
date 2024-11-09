package no.hiof.g13.adapters;

import no.hiof.g13.models.Question;
import no.hiof.g13.ports.out.CompetenceInputPort;

public class CompetenceInputAdapter implements CompetenceInputPort {

    @Override
    public String getUserInput() {
        return "pending input...";
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public int getAnswer(Question question, String input) {

        return 0;
    }

    @Override
    public int getUserConfig(int recommendedConfig) {
        return 0;
    }
}
