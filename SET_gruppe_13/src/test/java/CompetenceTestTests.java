import no.hiof.g13.models.CompetenceTest;
import no.hiof.g13.models.Question;
import no.hiof.g13.models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class CompetenceTestTests {

    private final InputStream orgIn = System.in;
    //CompetenceTest competenceTest = new CompetenceTest();

    @Mock
    User mockUser1;

    @Mock
    User mockUser2;

    @Mock
    User mockUser3;

    @Mock
    Question textChoiceQuestion;

    @Mock
    Question textChoiceQuestion2;

    @Mock
    Question numRangeQuestion;

    @AfterEach
    void tearDown() {
        System.setIn(orgIn);
    }


    @Test
    @Order(1)
    @DisplayName("Check if user has an active UI config")
    public void checkConfig() {

        CompetenceTest competenceTest = new CompetenceTest();

        // Arrange
        Mockito.when(mockUser1.getConfigId()).thenReturn(0);
        Mockito.when(mockUser2.getConfigId()).thenReturn(1);
        Mockito.when(mockUser3.getConfigId()).thenReturn(2);

        // Act
        boolean configFalse = competenceTest.checkConfig(mockUser1);
        boolean configTrue = competenceTest.checkConfig(mockUser2);
        boolean configTrue2 = competenceTest.checkConfig(mockUser3);

        // Assert
        Assertions.assertFalse(configFalse);
        Assertions.assertTrue(configTrue);
        Assertions.assertTrue(configTrue2);
    }

    @Test
    @DisplayName("Init test if input is NOT 'N'")
    public void initTest() {

        CompetenceTest competenceTest = new CompetenceTest();

        // Arrange
        String input = "Y\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Act
        boolean result = competenceTest.runTest();

        // Assert
        Assertions.assertTrue(result);

    }

    @Test
    @DisplayName("Skip test when input is 'N'")
    public void skipTest() {

        CompetenceTest competenceTest = new CompetenceTest();

        // Arrange
        String input = "N\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Act
        boolean result = competenceTest.runTest();

        // Assertions
        Assertions.assertFalse(result);

    }

    @Test
    @DisplayName("Add multiple questions objects to QuestionList")
    public void addMultipleQuestions() {


        // Arrange
        CompetenceTest competenceTest = new CompetenceTest();
        ArrayList<Question> expectedObjs = new ArrayList<>();

        // Act
        competenceTest.addQuestions(textChoiceQuestion, textChoiceQuestion2, numRangeQuestion);

        // Assert
        for(Question questionX : competenceTest.getQuestionList()) {
            expectedObjs.add(questionX);
        }
        Assertions.assertEquals(expectedObjs, competenceTest.getQuestionList());
    }

}
