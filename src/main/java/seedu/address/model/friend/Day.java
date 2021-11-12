package seedu.address.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_END_TIME_ORDER;
import static seedu.address.model.util.DayTimeUtil.getIndexFromTime;
import static seedu.address.model.util.DayTimeUtil.getTimeFromIndex;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;


/**
 * Represents a day in a schedule.
 */
public class Day {

    private static final int NUMBER_OF_SLOTS = 24;
    private final boolean[] timeSlots;
    private final DayOfWeek dayOfWeek;

    /**
     * Constructs a default {@code Day}.
     * Utilised by jackson for serialization
     */
    private Day() {
        this.timeSlots = new boolean[NUMBER_OF_SLOTS];
        this.dayOfWeek = DayOfWeek.of(1);
    }

    /**
     * Constructs a {@code Day}.
     *
     * @param dayOfWeek A day of the week.
     */
    public Day(DayOfWeek dayOfWeek) {
        requireNonNull(dayOfWeek);
        this.timeSlots = new boolean[NUMBER_OF_SLOTS];
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Constructs a {@code Day}.
     *
     * @param timeSlots Timeslots of day.
     * @param dayOfWeek A day of the week.
     */
    public Day(boolean[] timeSlots, DayOfWeek dayOfWeek) {
        this.timeSlots = timeSlots;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Checks if the time slot for the given {@code hourOfDay} is free.
     *
     * @param hourOfDay hour to check if free.
     * @return whether the day timeslot is free for given hour.
     */
    public boolean isTimeSlotHourFree(int hourOfDay) throws InvalidHourOfDayException {
        if (hourOfDay < 0 || hourOfDay > timeSlots.length) {
            throw new InvalidHourOfDayException();
        }
        return timeSlots[hourOfDay];
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

        if (endIndex <= startIndex) {
            throw new InvalidDayTimeException(MESSAGE_END_TIME_ORDER);
        }

        for (int i = startIndex; i <= endIndex - 1; i++) {
            timeSlots[i] = isFree;
        }
    }

    /**
     * Returns true if valid day.
     *
     * @param day Day to validate.
     * @return True if valid.
     */
    public static boolean isValidDay(Day day) {
        return day.dayOfWeek != null && day.timeSlots != null && day.timeSlots.length == 24;
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
     * Gets a copy of timeslots
     *
     * @return Copy of Timeslots
     */
    public boolean[] getTimeSlots() {
        return timeSlots.clone();
    }

    /**
     * Gets free time slots grouped.
     * eg. groups 0800-0900 (index 8) and 0900-1000 (index 9) to [0800, 1000]
     *
     * @return Grouped free timeslots
     */
    public List<String[]> getGroupedTimeSlots() throws InvalidDayTimeException {
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

        if (isContinuous) { // For last continuous time slot it displays 2400 (midnight)
            groupedSlots.add(new String[]{previousTimeSlot, getTimeFromIndex(24)});
        }

        return groupedSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // short-circuit if same object
        }

        if (!(o instanceof Day)) { // handles null
            return false;
        }

        Day day = (Day) o;
        return Arrays.equals(timeSlots, day.timeSlots) && dayOfWeek == day.dayOfWeek;
    }

    @Override
    public String toString() {
        return "Day{"
                + "timeSlots=" + Arrays.toString(timeSlots)
                + ", dayOfWeek=" + dayOfWeek.name()
                + '}';
    }
}
