package seedu.address.model.lesson;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a LessonTime for a Lesson in tuitiONE book.
 */
public class LessonTime {

    public static final long LESSON_PERIOD_IN_HOURS = 2;
    public static final LocalTime BOUNDED_START_TIME = LocalTime.of(9, 0);
    public static final LocalTime BOUNDED_END_TIME = LocalTime.of(21, 0).minusHours(LESSON_PERIOD_IN_HOURS);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    public static final String TIME_MESSAGE_CONSTRAINTS = String.format("Lesson can only be between %1$s to %2$s",
            BOUNDED_START_TIME.format(TIME_FORMATTER), BOUNDED_END_TIME.format(TIME_FORMATTER));
    public static final String DAY_MESSAGE_CONSTRAINT = "Day specified is not legitimate";

    public final DayOfWeek dayOfWeek;
    public final LocalTime startTime;
    public final LocalTime endTime; // computed field

    /**
     * Constructs a {@code LessonTime}.
     *
     * @param dayOfWeek A day of the week.
     * @param startTime A valid start time.
     */
    public LessonTime(DayOfWeek dayOfWeek, LocalTime startTime) {
        requireAllNonNull(dayOfWeek, startTime);
        checkArgument(isValidTime(startTime), TIME_MESSAGE_CONSTRAINTS);

        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = startTime.plusHours(LESSON_PERIOD_IN_HOURS);
    }

    /**
     * Returns true if a given timings are within bounded limits.
     */
    public static boolean isValidTime(LocalTime testStart) {
        if (testStart == null) {
            return false;
        }
        return testStart.equals(BOUNDED_START_TIME)
                || testStart.equals(BOUNDED_END_TIME)
                || (testStart.isAfter(BOUNDED_START_TIME) && testStart.isBefore(BOUNDED_END_TIME));
    }

    /**
     * Returns true if the lessons overlap in timing and day.
     */
    public boolean hasOverlappedTiming(LessonTime otherTiming) {
        LocalTime otherStartTime = otherTiming.startTime;
        LocalTime otherEndTime = otherTiming.endTime;

        boolean isClashingStartTime = (otherStartTime.isAfter(startTime) && otherStartTime.isBefore(endTime));
        boolean isClashingEndTime = (otherEndTime.isAfter(startTime) && otherEndTime.isBefore(endTime));
        boolean isStartingAtSameTime = (otherStartTime.equals(startTime));
        boolean isEndingAtSameTime = (otherEndTime.equals(endTime));
        boolean isOnSameDay = otherTiming.dayOfWeek.equals(dayOfWeek);

        if (!isOnSameDay) {
            return false;
        }
        return (isClashingStartTime || isClashingEndTime || isStartingAtSameTime || isEndingAtSameTime);
    }

    /**
     * Parses a {@code String day} into {@code DayOfWeek} if valid, else null.
     */
    public static DayOfWeek parseStringToDay(String day) {
        requireNonNull(day);
        switch (day) {
        case "Mon":
            return DayOfWeek.MONDAY;
        case "Tue":
            return DayOfWeek.TUESDAY;
        case "Wed":
            return DayOfWeek.WEDNESDAY;
        case "Thu":
            return DayOfWeek.THURSDAY;
        case "Fri":
            return DayOfWeek.FRIDAY;
        case "Sat":
            return DayOfWeek.SATURDAY;
        case "Sun":
            return DayOfWeek.SUNDAY;
        default:
            return null;
        }
    }

    /**
     * Returns String with corresponding to DayOfWeek.
     */
    public static String parseDayToString(DayOfWeek dayOfWeek) {
        requireNonNull(dayOfWeek);
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }

    @Override
    public int hashCode() {
        return hash(dayOfWeek, startTime, endTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LessonTime)) {
            return false;
        }
        LessonTime otherLessonTime = (LessonTime) other;
        return dayOfWeek.equals(otherLessonTime.dayOfWeek)
                && startTime.equals(otherLessonTime.startTime)
                && endTime.equals(otherLessonTime.endTime);
    }

    @Override
    public String toString() {
        return "";
    }
}
