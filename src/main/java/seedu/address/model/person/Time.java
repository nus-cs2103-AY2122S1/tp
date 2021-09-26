package seedu.address.model.person;

import seedu.address.model.person.exceptions.InvalidTimeException;

/**
 * Represents the start time, end time, and the date of a task
 */
public class Time implements Comparable<Time> {
    private int hour;
    private int min;

    /**
     * Constructor of time.
     * @param hour The hour part of the time represented by int.
     * @param min The min part of the time represented by int.
     * @throws InvalidTimeException Throws when the time is invalid.
     */
    public Time(int hour, int min) throws InvalidTimeException {
        if (hour < 0 || hour >= 24 || min < 0 || min >= 60) {
            throw new InvalidTimeException();
        }
        this.hour = hour;
        this.min = min;
    }

    /**
     * Returns a string representing the duration between this time and another time.
     * @param otherTime The other time compared to this.
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
        return hourDifference + " hour(s) " + minDifference + "min(s)";
    }

    /**
     * Gets the hour part of the time.
     *
     * @return The hour of the task.
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Gets the min part of the time.
     *
     * @return The min of the time.
     */
    public int getMin() {
        return this.min;
    }

    /**
     * Compares another Time with this time.
     * @param other The other time.
     * @return 1 if this time is later. 0 if this time and the other time are the same
     * moment. -1 if this time is earlier.
     */
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
