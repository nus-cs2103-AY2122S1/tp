package seedu.address.model.person;

import java.util.Comparator;

import seedu.address.model.person.exceptions.InvalidTimeException;

/**
 * Represents the start time, end time, and the date of a task
 */
public class Time implements Comparable<Time> {
    private int hour;
    private int min;

    public Time(int hour, int min) throws InvalidTimeException {
        if (hour < 0 || hour >= 24 || min < 0 || min >= 60) {
            throw new InvalidTimeException();
        }
        this.hour = hour;
        this.min = min;
    }

    /**
     * Returns a string representing the duration between this time and another time
     */
    public String comparedToOther(Time otherTime) {
        Time laterTime = this.compareTo(otherTime) >= 0 ? this : otherTime;
        Time earlierTime = this == laterTime ? otherTime : this;

        int hourDifference = laterTime.getHour() - earlierTime.getHour();
        int minDifference = laterTime.getMin() - earlierTime.getMin();

        if (minDifference < 0) {
            minDifference += 60;
            hourDifference -= 1;
        }
        return hourDifference +" hour(s) " + minDifference + "min(s)";
    }

    public int getHour() {
        return this.hour;
    }

    public int getMin() {
        return this.min;
    }

    public int compareTo(Time other) {
        if (this.getHour() == other.getHour() && this.getMin() == other.getMin()) {
            return 0;
        } else if (this.getHour() > other.getHour()) {
            return 1;
        } else if (this.getHour() < other.getHour()) {
            return -1;
        } else if (this.getMin() > other.getMin()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String hourStr = Integer.toString(hour);
        String minStr = Integer.toString(min);
        if (hour < 10) {
            hourStr = "0" + hourStr;
        }
        if (min < 10) {
            minStr = "0" + minStr;
        }
        return hourStr + ":" + minStr;
    }
}
