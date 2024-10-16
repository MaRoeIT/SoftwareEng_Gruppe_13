import no.hiof.g13.models.CompetenceTest;
import no.hiof.g13.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CompetenceTestTests {

    @Mock
    User mockUserNoConfig;

    @Mock
    User mockUserConfig;

    @Test
    @DisplayName("Check if user has an active UI config")
    public void checkConfig() {

        // Arrange
        CompetenceTest competenceTest = new CompetenceTest();


        when(mockUserNoConfig.getConfigId()).thenReturn(0);

        // Act
        int configId = competenceTest.checkConfig(mockUserNoConfig);

        // Assert
        Assertions.assertEquals(0, configId);
    }
}
