package no.hiof.g13.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

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
