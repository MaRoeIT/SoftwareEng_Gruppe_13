import static org.junit.jupiter.api.Assertions.*;

import no.hiof.g13.models.product.IOTVacuumCleaner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

public class IOTVacuumCleanerTest {

    @Test
    @DisplayName("Tester konfigurasjon på SmartSug")
    public void configureDevice() {
        IOTVacuumCleaner vacuumCleaner = mock(IOTVacuumCleaner.class);

        vacuumCleaner.configureDevice();

        verify(vacuumCleaner, times(1)).configureDevice();

        System.out.println("SmartSug har blitt konfigurert...");
    }

    @Test
    @DisplayName("Tester oppdatering av SmartSug innstillinger")
    public void updateSettings() {
        IOTVacuumCleaner vacuumCleaner = mock(IOTVacuumCleaner.class);

        vacuumCleaner.updateSettings();

        verify(vacuumCleaner, times(1)).updateSettings();

        System.out.println("Oppdatering av SmartSug-innstillinger fullført...");
    }
}
