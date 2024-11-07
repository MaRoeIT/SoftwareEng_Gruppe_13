import no.hiof.g13.models.CompetenceReview;
import no.hiof.g13.models.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class QuestionsListTests {

    @Mock
    Question singleChoiceQuestion;

    @Mock
    Question singleChoiceQuestion2;

    @Mock
    Question numRangeQuestion;


    @Test
    @DisplayName("Add multiple questions objs")
    public void addMultipleQuestions() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();
        ArrayList<Question> expectedObjs = new ArrayList<>();

        // Act
        competenceReview.addQuestions(singleChoiceQuestion, singleChoiceQuestion2, numRangeQuestion);

        // Assert
        for(Question questionX : competenceReview.getQuestionsList()) {
            expectedObjs.add(questionX);
        }
        Assertions.assertEquals(expectedObjs, competenceReview.getQuestionsList());
    }

    @Test
    @DisplayName("Add one question obj")
    public void addOneQuestion() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();
        ArrayList<Question> expectedObjs = new ArrayList<>();

        // Act
        competenceReview.addQuestions(singleChoiceQuestion);

        // Assert
        for(Question questionX : competenceReview.getQuestionsList()) {
            expectedObjs.add(questionX);
        }
        Assertions.assertEquals(expectedObjs, competenceReview.getQuestionsList());
    }

    @Test
    @DisplayName("No question objs get added")
    public void addNoQuestion() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();
        ArrayList<Question> expectedObjs = new ArrayList<>();

        // Act
        competenceReview.addQuestions();

        // Assert
        for(Question questionX : competenceReview.getQuestionsList()) {
            expectedObjs.add(questionX);
        }
        Assertions.assertEquals(expectedObjs, competenceReview.getQuestionsList());
    }
}
