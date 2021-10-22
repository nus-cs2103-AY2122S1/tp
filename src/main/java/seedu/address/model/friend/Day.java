package seedu.address.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.util.DayTimeUtil.getIndexFromTime;
import static seedu.address.model.util.DayTimeUtil.getTimeFromIndex;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;


/**
 * Represents a day in a schedule.
 */
public class Day {

    private final boolean[] timeSlots;
    private final DayOfWeek dayOfWeek;

    /**
     * Constructs a {@code Day}.
     *
     * @param dayOfWeek A day of the week.
     */
    public Day(DayOfWeek dayOfWeek) {
        requireNonNull(dayOfWeek);
        this.timeSlots = new boolean[24];
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Sets the timeslots in the day from the range of startTime to endTime to isFree.
     *
     * @param startTime Start of timeslot.
     * @param endTime   End of timeslot.
     * @param isFree    Is timeslot free.
     * @throws InvalidDayTimeException Invalid time.
     */
    public void setTime(String startTime, String endTime, boolean isFree) throws InvalidDayTimeException {
        int startIndex = getIndexFromTime(startTime);
        int endIndex = getIndexFromTime(endTime);

        if (endIndex < startIndex) {
            throw new InvalidDayTimeException("End time cannot be before start time");
        }

        for (int i = startIndex; i <= endIndex; i++) {
            timeSlots[i] = isFree;
        }
    }

    /**
     * Gets day name.
     *
     * @return Name of day.
     */
    public String getDayName() {
        return dayOfWeek.name();
    }

    /**
     * Gets free time slots grouped.
     * eg. groups 0800 0900 1000 to [0800, 1000]
     *
     * @return Grouped free timeslots
     */
    public ArrayList<String[]> getTimeSlots() throws InvalidDayTimeException {
        ArrayList<String[]> groupedSlots = new ArrayList<>();

        String previousTimeSlot = "";
        boolean isContinuous = false;

        for (int i = 0; i < timeSlots.length; i++) {
            if (!isContinuous && timeSlots[i]) { // start of new continuous slot
                previousTimeSlot = getTimeFromIndex(i);
                isContinuous = true;
            } else if (isContinuous && !timeSlots[i]) { // end of continuous slot
                groupedSlots.add(new String[]{previousTimeSlot, getTimeFromIndex(i)});
                isContinuous = false;
            }
        }

        if (isContinuous) { // For last continuous time slot it wraps around to 0000
            groupedSlots.add(new String[]{previousTimeSlot, getTimeFromIndex(0)});
        }

        return groupedSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Arrays.equals(timeSlots, day.timeSlots) && dayOfWeek == day.dayOfWeek;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dayOfWeek);
        result = 31 * result + Arrays.hashCode(timeSlots);
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "timeSlots=" + Arrays.toString(timeSlots) +
                ", dayOfWeek=" + dayOfWeek.name() +
                '}';
    }
}
