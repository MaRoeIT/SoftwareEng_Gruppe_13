package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TimeScheduler {

    private HashMap<DayTime, DayTime> schedule = new HashMap<>();

    public void addScheduledRun(DayTime startTime, DayTime endTime) {
        this.schedule.put(startTime, endTime);
    }

    public HashMap<DayTime,DayTime> getSchedule(){
        return schedule;
    }
}
