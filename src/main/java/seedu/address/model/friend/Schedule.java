package seedu.address.model.friend;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Represents a Friend's schedule in gitGud's friend list.
 */
public class Schedule {
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
     * Sets the schedule of the day.
     *
     * @param day       Day to set
     * @param startTime Start of timeslot.
     * @param endTime   End of timeslot.
     * @param isFree    Is timeslot free.
     * @throws InvalidDayTimeException Invalid time or day.
     */
    public void setScheduleDay(int day, String startTime, String endTime, boolean isFree) throws InvalidDayTimeException {
        if (day < 1 || day > 7) {
            throw new InvalidDayTimeException("Invalid day value");
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
        return "Schedule{" +
                "schedule=" + schedule +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule1 = (Schedule) o;
        return Objects.equals(schedule, schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }
}
