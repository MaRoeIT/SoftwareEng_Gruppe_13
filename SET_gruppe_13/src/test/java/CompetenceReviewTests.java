import net.bytebuddy.dynamic.scaffold.MethodGraph;
import no.hiof.g13.models.*;
import no.hiof.g13.ports.out.CompetenceInputPort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DecimalFormat;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompetenceReviewTests {

    @Mock
    private CompetenceInputPort mockUserInput;

    @Mock
    User mockUser1;

    @Mock
    User mockUser2;

    @Mock
    User mockUser3;

    @Mock
    Question singleChoiceQuestion;

    @Mock
    Question singleChoiceQuestion2;

    @Mock
    Question numRangeQuestion;

    Map<String, Integer> choices1 = Map.of("answer 1", 1, "answer 2", 2, "answer 3", 3);
    Map<String, Integer> choices2 = Map.of("answer 1", 3, "answer 2", 1, "answer 3", 2, "answer 4", 4);
    Map<String, Integer> choices3 = Map.of("1", 1, "2", 2, "3", 3, "4", 4, "5", 5);

    @Test
    @DisplayName("Init review if any button but 'skip' is pressed")
    public void initReview() {
        // Arrange
        CompetenceReview competenceTest = new CompetenceReview(mockUserInput);

        when(mockUserInput.getUserInput()).thenReturn(UUID.randomUUID().toString());

        // Act
        boolean result = competenceTest.initReview(mockUserInput.getUserInput());

        // Assert
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Skip review when 'skip' button is pressed")
    public void skipReview() {
        // Arrange
        CompetenceReview competenceTest = new CompetenceReview(mockUserInput);

        when(mockUserInput.getUserInput()).thenReturn("skip");

        // Act
        boolean result = competenceTest.initReview(mockUserInput.getUserInput());

        // Assertions
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Verify collected score")
    public void verifyAddedScore() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview(mockUserInput);

        // Mock a random input
        when(mockUserInput.getUserInput()).thenReturn(UUID.randomUUID().toString());

        // Add questions
        competenceReview.addQuestions(singleChoiceQuestion, singleChoiceQuestion2, numRangeQuestion);

        doReturn(3).when(mockUserInput).getAnswer(eq(singleChoiceQuestion), anyString());
        doReturn(1).when(mockUserInput).getAnswer(eq(singleChoiceQuestion2), anyString());
        doReturn(8).when(mockUserInput).getAnswer(eq(numRangeQuestion), anyString());

        // Act
        competenceReview.runReview();

        // Assert
        List<Integer> expectedScores = Arrays.asList(3, 1, 8);
        Assertions.assertEquals(3, competenceReview.getTrackScore().size(), "Size of the list");
        Assertions.assertEquals(expectedScores, competenceReview.getTrackScore(), "Excpected scores returned ");
    }

    @Test
    @DisplayName("Sum total score")
    public void sumScore() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();
        List<Integer> scoreFromReview = new ArrayList<Integer>();
        Collections.addAll(scoreFromReview, 3, 1, 8);

        // Act
        int sum = competenceReview.sumScore(scoreFromReview);

        // Assert
        Assertions.assertEquals(12, sum);
    }

    @Test
    @DisplayName("Verify best possible outcome")
    public void verifyBestResult() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();
        competenceReview.addQuestions(singleChoiceQuestion, singleChoiceQuestion2, numRangeQuestion);

        when(singleChoiceQuestion.getChoices()).thenReturn(choices1);
        when(singleChoiceQuestion2.getChoices()).thenReturn(choices2);
        when(numRangeQuestion.getChoices()).thenReturn(choices3);

        // Act
        int bestScore = competenceReview.getBestResult();

        // Assert
        Assertions.assertEquals(12, bestScore);
    }

    @Test
    @DisplayName("Calculate the right results")
    public void calculateResults() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();

        // Act
        int passed = competenceReview.calculateResult(9, 12, 50.0);
        int lowestResult = competenceReview.calculateResult(3, 12, 50.0);
        int bestResult = competenceReview.calculateResult(12, 12, 50.0);

        // Assert
        Assertions.assertEquals(2, passed);
        Assertions.assertEquals(1, lowestResult);
        Assertions.assertEquals(2, bestResult);
    }

    @Test
    @DisplayName("Verify that user config is set")
    public void setUserConfig() {
        // Arrange
        CompetenceReview competenceReview = new CompetenceReview();
        CompetenceReview spyCompetenceReview = spy(competenceReview);

        int expectedVal = 2;
        int expectedVal2 = 1;

        // Act
        spyCompetenceReview.setUserConfig(mockUser1, expectedVal);
        spyCompetenceReview.setUserConfig(mockUser2, expectedVal2);

        // Assert
        verify(spyCompetenceReview, times(1)).setUserConfig(mockUser1, expectedVal);
        verify(spyCompetenceReview, times(1)).setUserConfig(mockUser2, expectedVal2);
    }
}
