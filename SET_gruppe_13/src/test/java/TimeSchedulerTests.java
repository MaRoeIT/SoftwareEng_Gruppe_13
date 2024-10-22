import no.hiof.g13.models.DayTime;
import no.hiof.g13.models.TimeScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class TimeSchedulerTests {
    @Mock
    DayTime startPoint;

    @Mock
    DayTime endPoint;

    @Test
    @DisplayName("Add Weekly shcedule")
    public void addWeeklySchedule(){
        TimeScheduler timeScheduler = new TimeScheduler();


        timeScheduler.addScheduledRun(startPoint, endPoint);

        HashMap<DayTime, DayTime> expected = new HashMap<>();
        expected.put(startPoint, endPoint);
        Assertions.assertEquals(expected, timeScheduler.getSchedule());
    }
}
