package seedu.address.model.friend;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;

/**
 * Represents a Friend's schedule in gitGud's friend list.
 */
public class Schedule {

    public static final String MESSAGE_INVALID_SCHEDULE = "Schedule must contain 7 valid days, from Monday to Sunday.";

    private static final String MESSAGE_DAYTIME_INVALID_RANGE =
            "Day value must be an Integer within 1 - 7 (inclusive).";
    private static final int SCHEDULE_START_INDEX = 1;
    private static final int SCHEDULE_END_INDEX = 7;
    private static final int EXPECTED_LIST_LENGTH = (SCHEDULE_END_INDEX - SCHEDULE_START_INDEX + 1);
    private final ArrayList<Day> daysOfWeek;

    /**
     * Constructs a {@code Schedule}.
     */
    public Schedule() {
        this.daysOfWeek = new ArrayList<>();
        for (int i = SCHEDULE_START_INDEX; i <= SCHEDULE_END_INDEX; i++) {
            daysOfWeek.add(new Day(DayOfWeek.of(i)));
        }
    }

    /**
     * Constructs a copy of a {@code Schedule}.
     *
     * @param schedule Schedule
     */
    public Schedule(Schedule schedule) {
        this.daysOfWeek = new ArrayList<>();
        // if invalid schedule passed in, create empty schedule
        if (!isValidSchedule(schedule)) {
            for (int i = SCHEDULE_START_INDEX; i <= SCHEDULE_END_INDEX; i++) {
                daysOfWeek.add(new Day(DayOfWeek.of(i)));
            }
        } else {
            for (int i = SCHEDULE_START_INDEX; i <= SCHEDULE_END_INDEX; i++) {
                daysOfWeek.add(new Day(schedule.daysOfWeek.get(i - 1).getTimeSlots(), DayOfWeek.of(i)));
            }
        }
    }

    /**
     * Returns true if valid schedule.
     *
     * @param schedule Schedule to validate.
     * @return True if valid.
     */
    public static boolean isValidSchedule(Schedule schedule) {
        if (schedule.daysOfWeek.size() != EXPECTED_LIST_LENGTH) {
            return false;
        }
        boolean isValid = true;
        for (int i = SCHEDULE_START_INDEX; i <= SCHEDULE_END_INDEX; i++) {
            Day current = schedule.daysOfWeek.get(i - 1);
            isValid = isValid && Day.isValidDay(current) && current.getDayName().equals(DayOfWeek.of(i).name());
        }
        return isValid;
    }

    /**
     * Checks if schedule timeslot is free for given {@code dayOfWeek} and {@code hourOfDay}.
     *
     * @param dayOfWeek day to check if is free for.
     * @param hourOfDay hour to check if is free for.
     * @return whether schedule is free for given {@code dayOfWeek} and {@code hourOfDay}.
     * @throws InvalidDayTimeException   thrown when dayOfWeek given exceeds valid range.
     * @throws InvalidHourOfDayException thrown when hourOfDay given exceeds valid range.
     */
    public boolean isTimeSlotAvailable(int hourOfDay, int dayOfWeek)
            throws InvalidDayTimeException, InvalidHourOfDayException {
        if (dayOfWeek < SCHEDULE_START_INDEX || dayOfWeek > SCHEDULE_END_INDEX) {
            throw new InvalidDayTimeException(MESSAGE_DAYTIME_INVALID_RANGE);
        }
        return daysOfWeek.get(convertToListIndex(dayOfWeek)).isTimeSlotHourFree(hourOfDay);
    }

    /**
     * Sets the schedule of the day.
     *
     * @param day       Day to set.
     * @param startTime Start of timeslot.
     * @param endTime   End of timeslot.
     * @param isFree    Is timeslot free.
     * @throws InvalidDayTimeException Invalid time or day.
     */
    public void setScheduleDay(int day, String startTime, String endTime, boolean isFree)
            throws InvalidDayTimeException {
        if (day < SCHEDULE_START_INDEX || day > SCHEDULE_END_INDEX) {
            throw new InvalidDayTimeException(MESSAGE_DAYTIME_INVALID_RANGE);
        }
        daysOfWeek.get(convertToListIndex(day)).setTime(startTime, endTime, isFree);
    }

    /**
     * Returns a list of days
     *
     * @return ObservableList of days
     */
    public ObservableList<Day> getSchedule() {
        return FXCollections.observableList(daysOfWeek);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule1 = (Schedule) o;
        return Objects.equals(daysOfWeek, schedule1.daysOfWeek);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Day day : daysOfWeek) {
            builder.append("\nDay: ")
                    .append(day.getDayName())
                    .append("; Free TimeSlots: ");
            try {
                day.getGroupedTimeSlots().forEach(timeSlot -> builder.append(Arrays.toString(timeSlot)).append(", "));
            } catch (InvalidDayTimeException e) {
                builder.append("Could not display timeslots");
            }
            builder.append(";");
        }
        return builder.toString();
    }

    private int convertToListIndex(int dayOfWeek) {
        return dayOfWeek - 1;
    }
}
