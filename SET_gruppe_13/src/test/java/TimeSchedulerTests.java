import no.hiof.g13.models.DayTime;
import no.hiof.g13.models.TimeScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        ArrayList<ArrayList<DayTime>> expected = new ArrayList<>();
        ArrayList<DayTime> startPointList = new ArrayList<>();
        ArrayList<DayTime> endPointList = new ArrayList<>();

        startPointList.add(startPoint);
        endPointList.add(endPoint);

        expected.add(startPointList);
        expected.add(endPointList);

        Assertions.assertEquals(expected, timeScheduler.getSchedule());
    }

    private static Stream<Arguments> checkDayInScheduleArguments(){
        TimeScheduler timeScheduler = new TimeScheduler();
        DayTime startPoint1 = mock(DayTime.class);
        when(startPoint1.getDay()).thenReturn(1);
        DayTime startPoint2 = mock(DayTime.class);
        when(startPoint2.getDay()).thenReturn(2);
        DayTime startPoint3 = mock(DayTime.class);
        when(startPoint3.getDay()).thenReturn(3);
        DayTime startPoint4 = mock(DayTime.class);
        when(startPoint4.getDay()).thenReturn(4);
        DayTime startPoint5 = mock(DayTime.class);
        when(startPoint5.getDay()).thenReturn(5);
        DayTime startPoint6 = mock(DayTime.class);
        when(startPoint6.getDay()).thenReturn(6);
        DayTime startPoint7 = mock(DayTime.class);
        when(startPoint7.getDay()).thenReturn(7);

        DayTime endPoint1 = mock(DayTime.class);
        when(endPoint1.getDay()).thenReturn(1);
        DayTime endPoint2 = mock(DayTime.class);
        when(endPoint2.getDay()).thenReturn(1);
        DayTime endPoint3 = mock(DayTime.class);
        when(endPoint3.getDay()).thenReturn(4);
        DayTime endPoint4 = mock(DayTime.class);
        when(endPoint4.getDay()).thenReturn(7);
        DayTime endPoint5 = mock(DayTime.class);
        when(endPoint5.getDay()).thenReturn(9);
        DayTime endPoint6 = mock(DayTime.class);
        when(endPoint6.getDay()).thenReturn(1);
        DayTime endPoint7 = mock(DayTime.class);
        when(endPoint7.getDay()).thenReturn(2);

        timeScheduler.addScheduledRun(startPoint1, endPoint1);
        timeScheduler.addScheduledRun(startPoint2, endPoint2);
        timeScheduler.addScheduledRun(startPoint3, endPoint3);
        timeScheduler.addScheduledRun(startPoint4, endPoint4);
        timeScheduler.addScheduledRun(startPoint5, endPoint5);
        timeScheduler.addScheduledRun(startPoint6, endPoint6);
        timeScheduler.addScheduledRun(startPoint7, endPoint7);

        return Stream.of(
                Arguments.of(timeScheduler, 1, timeScheduler.getSchedule().get(1).get(1).getDay())
        );
    }

    @ParameterizedTest(name = "Start point {1}, end point {2}")
    @MethodSource("checkDayInScheduleArguments")
    @DisplayName("Check day in schedule")
    public void checkDayInSchedule(TimeScheduler timeScheduler, int expectedDay, int actualDay){
        assertEquals(expectedDay, actualDay);
    }
}
