package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.List;

public class CompetenceTest {
    private int configId;
    private UserDummy currentUser;
    private List<Integer> trackResult;
    private ArrayList<Question> questions;

    public CompetenceTest() {
        this.configId = -1;
        this.questions = new ArrayList<Question>();
    }

    public int getConfigId() {
        return this.configId;
    }
    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public UserDummy getCurrentUser() {
        return this.currentUser;
    }
    public void setCurrentUser(UserDummy currentUser) {
        this.currentUser = currentUser;
    }

}
