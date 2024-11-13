import no.hiof.g13.DTO.QuestionDTO;
import no.hiof.g13.models.*;
import org.junit.jupiter.api.*;

import java.util.*;


public class QuestionDTOTests {

    @Test
    @DisplayName("tooDomain maps DTO to core correctly")
    public void tooDomainMapsCorrect() {
        // Arrange
        int questionId = 1;
        String description = "Hvor kjent er du med å teste DTOs?";
        int choicesId = 4;
        List<String> choices = Arrays.asList("Ikke kjent", "Litt kjent", "kjent", "Veldig kjent");
        QuestionDTO dto = new QuestionDTO(questionId, description, choicesId, choices);

        // Act
        Question question = dto.toDomain();

        // Assert
        Assertions.assertNotNull(question);
        Assertions.assertEquals(description, question.getQuestionDescription());
        Assertions.assertEquals(choicesId, question.getChoicesId());

        Map<String, Integer> choices_alternatives = question.getChoices();
        Assertions.assertEquals(4, choices_alternatives.size());
        Assertions.assertEquals(1, choices_alternatives.get("Ikke kjent"));
        Assertions.assertEquals(2, choices_alternatives.get("Litt kjent"));
        Assertions.assertEquals((3), choices_alternatives.get("kjent"));
        Assertions.assertEquals(4, choices_alternatives.get("Veldig kjent"));
    }

    @Test
    @DisplayName("tooDomain returns empty choices")
    public void tooDomain_emptyChoices() {
        // Arrange
        int questionId = 1;
        String description = "Synes du det er gøy å lage enhetstester?";
        int choicesId = 4;
        List<String> choices = Arrays.asList();
        QuestionDTO dto = new QuestionDTO(questionId, description, choicesId, choices);

        // Act
        Question question = dto.toDomain();

        // Assert
        Assertions.assertTrue(question.getChoices().isEmpty());

    }

}