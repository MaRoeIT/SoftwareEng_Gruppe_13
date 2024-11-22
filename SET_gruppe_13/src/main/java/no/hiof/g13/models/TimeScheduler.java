package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Class to make schedules for the IOTDevice so the IOT device can run even on times when your not home.
 * Example:
 * <pr>
 * TimeScheduler vacumCleanerSchedule = new TimeSchedule();
 *
 * vacumCleanerSchedule.addScheduledRun(new DayTime(1,13,30), new DayTime(1, 14, 30))
 * </pr>
 * This will make it have a schedule for the first day in the week from 13:30 to 14:30
 */
public class TimeScheduler {

    private ArrayList<DayTime> startTime = new ArrayList<>();
    private ArrayList<DayTime> endTime = new ArrayList<>();
    private ArrayList<ArrayList<DayTime>> schedule = new ArrayList<>();

    public void addScheduledRun(DayTime startTime, DayTime endTime) {
        this.startTime.add(startTime);
        this.endTime.add(endTime);
        Collections.addAll(schedule,this.startTime,this.endTime);
    }

    public ArrayList<ArrayList<DayTime>> getSchedule(){
        return schedule;
    }
}
