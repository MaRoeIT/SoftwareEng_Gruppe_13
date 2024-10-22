import no.hiof.g13.models.TimeScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TimeSchedulerTests {

    @Test
    @DisplayName("Add Weekly shcedule")
    public void addWeeklySchedule(){
        TimeScheduler timeScheduler = new TimeScheduler();

        timeScheduler.addScheduledRun();

        int expected = 1;
        Assertions.assertEquals(expected, timeScheduler.getSchedule());
    }
}
