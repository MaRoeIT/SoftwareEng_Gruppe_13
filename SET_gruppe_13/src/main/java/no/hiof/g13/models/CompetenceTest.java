package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.List;

public class CompetenceTest {
    private int configId;
    private List<Integer> trackResult;
    private ArrayList<Question> questions;

    public CompetenceTest() {
        this.questions = new ArrayList<Question>();
    }

    public int getConfigId() {
        return this.configId;
    }
    public void setConfigId(int configId) {
        this.configId = configId;
    }

    // Sjekker om bruker har an aktiv config for UI oppsett
    public int checkConfig(User currentUser) {
        if(currentUser.getConfigId() > 0) {
            return currentUser.getConfigId();
        }
        else {
            System.out.println("No config found, running competence test...");
            return 0;
        }
    }

}
