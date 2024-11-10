import no.hiof.g13.models.CheckUIConfig;
import no.hiof.g13.adapters.CompetenceInputAdapter;
import no.hiof.g13.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckUIConfigTests {

    @Mock
    private CompetenceInputAdapter mockCompetenceInput;

    @Mock
    User mockUser1;

    @Mock
    User mockUser2;

    @Mock
    User mockUser3;


    @Test
    @DisplayName("user have no UI config")
    public void NoConfigFound() {
        // Arrange
        CheckUIConfig checkUIConfig = new CheckUIConfig(mockCompetenceInput);
        when(mockUser1.getUserLevel()).thenReturn(0);

        // Act
        boolean configFalse = checkUIConfig.checkConfig(mockUser1);

        // Assert
        Assertions.assertFalse(configFalse);
    }

    @Test
    @DisplayName("User has active config")
    public void configFound() {
        // Arrange
        CheckUIConfig checkUIConfig = new CheckUIConfig(mockCompetenceInput);
        when(mockUser2.getUserLevel()).thenReturn(1);
        when(mockUser3.getUserLevel()).thenReturn(2);

        // Act
        boolean configTrue = checkUIConfig.checkConfig(mockUser2);
        boolean configTrue2 = checkUIConfig.checkConfig(mockUser3);

        // Assert
        Assertions.assertTrue(configTrue);
        Assertions.assertTrue(configTrue2);
    }

}
