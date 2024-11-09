package no.hiof.g13.models;

import no.hiof.g13.adapters.CompetenceInputAdapter;
import no.hiof.g13.ports.out.CompetenceInputPort;

public class CheckUIConfig {
    private CompetenceInputPort competenceInputPort;

    public CheckUIConfig() {
    }

    public CheckUIConfig(CompetenceInputAdapter competenceInputPort) {
        this.competenceInputPort = competenceInputPort;
    }

    public CompetenceInputPort getCompetenceInputHandler() {
        return this.competenceInputPort;
    }

    // Sjekker om bruker har an aktiv config for UI oppsett
    public boolean checkConfig(User currentUser) {
        if(currentUser.getConfigId() > 0) {
            System.out.println("Config found, continue login...");
            return true;
        }
        System.out.println("No config found, init test...");
        System.out.println("We strongly reccommend that you take a test. Press 'skip' to skip test: ");

        return false;
    }
}
