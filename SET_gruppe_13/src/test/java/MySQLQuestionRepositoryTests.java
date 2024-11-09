import no.hiof.g13.adapters.MySQLQuestionRepository;
import no.hiof.g13.models.Question;
import no.hiof.g13.ports.out.QuestionRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MySQLQuestionRepositoryTests {

    @Test
    @DisplayName("Existing questionId should return correct Question")
    public void getValidQuestionById() {

        // Arrange
        QuestionRepositoryPort repositoryPort = new MySQLQuestionRepository();
        int questionId = 1;

        // Act
        Question question = repositoryPort.getQuestionById(questionId);

        // Assert
        Assertions.assertNotNull(question);
        Assertions.assertEquals("Hvor komfortabel er du med å bruke smarttelefonen til å kontrollere enheter i hjemmet ditt?",
                question.getQuestionDescription());
        Assertions.assertEquals(1, question.getChoicesId());
        Map<String, Integer> choices = question.getChoices();
        Assertions.assertEquals(4, choices.size());
        Assertions.assertEquals(1, choices.get("Ikke komfortabel"));
        Assertions.assertEquals(2, choices.get("Litt komfortabel"));
        Assertions.assertEquals(3, choices.get("Komfortabel"));
        Assertions.assertEquals(4, choices.get("Svært komfortabel"));
    }

    @Test
    @DisplayName("Non-existing questionId should throw exception")
    public void getQuestionNoValidId() {
        // Arrange
        QuestionRepositoryPort repositoryPort = new MySQLQuestionRepository();
        int questionId = 9999;

        // Act and Assert
        try  {
            Question question = repositoryPort.getQuestionById(questionId);
            Assertions.fail("Excpected RuntimeException was not thrown");
        }
        catch (RuntimeException e) {
            Assertions.assertTrue(e.getMessage().contains("No question found with ID " + questionId));
        }
    }
}