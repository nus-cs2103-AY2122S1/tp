package seedu.address.model.friend;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Represents a Friend's schedule in gitGud's friend list.
 */
public class Schedule {

    public static final String MESSAGE_INVALID_SCHEDULE = "Schedule must contain 7 valid days,from Monday to Sunday.";

    private final ArrayList<Day> schedule;

    /**
     * Constructs a {@code Schedule}.
     */
    public Schedule() {
        this.schedule = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            schedule.add(new Day(DayOfWeek.of(i)));
        }
    }

    /**
     * Returns true if valid schedule.
     *
     * @param schedule Schedule to validate.
     * @return True if valid.
     */
    public static boolean isValidSchedule(Schedule schedule) {
        if (schedule.getSchedule().size() != 7) {
            return false;
        }
        boolean isValid = true;
        for (int i = 1; i <= 7; i++) {
            Day current = schedule.getSchedule().get(i - 1);
            isValid = isValid && Day.isValidDay(current) && current.getDayName().equals(DayOfWeek.of(i).name());
        }
        return isValid;
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
        if (day < 1 || day > 7) {
            throw new InvalidDayTimeException("Day value must be an Integer within 1 - 7 (inclusive).");
        }
        schedule.get(day - 1).setTime(startTime, endTime, isFree);
    }

    /**
     * Returns an immutable day list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Day> getSchedule() {
        return Collections.unmodifiableList(schedule);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Day day : schedule) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule1 = (Schedule) o;
        return Objects.equals(schedule, schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }
}
