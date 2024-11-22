package no.hiof.g13.models;

/**
 * A class to make a custom datatype object for use with the TimeScheduler. Holds the data for a specific day
 * so you can use it to create a start and stop time on the TimeScheduler.
 */
public class DayTime {
    private int day;
    private int hour;
    private int minute;

    public DayTime(int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setAllValues(int day, int hour, int minute){
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
}
