package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents an Appointment's date and time in the plannermd.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentDateTime(String, String)}
 */
public class AppointmentDateTime {

    public static final String DATE_MESSAGE_CONSTRAINTS = "Dates should be of the format DD/MM/YYYY "
            + "and adhere to the following constraints:\n"
            + "1. Must be a valid date (after now)\n"
            + "2. Day must be between 1-31 (0 in front of single digit is optional)\n"
            + "3. Month must be between 1-12 (0 in front of single digit is optional)\n"
            + "4. Year must be 4 characters.";

    public static final String TIME_MESSAGE_CONSTRAINTS = "Times should be of the format HH:MM "
            + "and adhere to the following constraints:\n"
            + "1. Must be a valid time (after now if the given date is the current date)\n"
            + "2. Hour must be between 0-23 (0 in front of single digit is optional)\n"
            + "3. Minute must be between 0-59 (0 in front of single digit is optional).";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    public static final DateTimeFormatter DISPLAYED_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:m")
            .withResolverStyle(ResolverStyle.SMART);

    public static final DateTimeFormatter DISPLAYED_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public final LocalDate date;
    public final LocalTime time;

    /**
     * Constructs an {@code AppointmentDateTime}.
     *
     * @param date A valid date.
     * @param time A valid time.
     */
    public AppointmentDateTime(String date, String time) {
        requireNonNull(date);
        requireNonNull(time);

        checkArgument(isValidDate(date), DATE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidTime(time, LocalDate.parse(date, DATE_FORMATTER)), TIME_MESSAGE_CONSTRAINTS);

        this.date = LocalDate.parse(date, DATE_FORMATTER);
        this.time = LocalTime.parse(time, TIME_FORMATTER);
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate date = LocalDate.parse(test, DATE_FORMATTER);
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns if a given string is a valid time.
     * If the input date is before the current date, return false.
     * Otherwise, checks if the given string is a valid time.
     *
     * @param test Input string representing a time.
     * @param date Input date.
     */
    public static boolean isValidTime(String test, LocalDate date) {
        try {
            LocalTime time = LocalTime.parse(test, TIME_FORMATTER);
            if (date.isBefore(LocalDate.now())) {
                return false;
            } else if (date.isEqual(LocalDate.now())) {
                return time.isAfter(LocalTime.now());
            } else {
                return true;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns if the given strings of date and time are valid.
     */
    public static boolean isValidAppointmentDateTime(String testDate, String testTime) {
        return isValidDate(testDate) && isValidTime(testTime, LocalDate.parse(testDate, DATE_FORMATTER));
    }

    /**
     * Checks if the two intervals of date/time are overlapping.
     * There is an overlap if both intervals have at least one point of date/time in common.
     *
     * @param firstStart  Start of first date/time interval.
     * @param firstEnd    End of first date/time interval.
     * @param secondStart Start of second date/time interval.
     * @param secondEnd   End of second date/time interval.
     * @return True if the intervals are overlapping. false otherwise.
     */
    public static boolean isClash(AppointmentDateTime firstStart, AppointmentDateTime firstEnd,
                                  AppointmentDateTime secondStart, AppointmentDateTime secondEnd) {
        LocalDate firstStartDate = firstStart.date;
        LocalTime firstStartTime = firstStart.time;
        LocalDate firstEndDate = firstEnd.date;
        LocalTime firstEndTime = firstEnd.time;
        LocalDate secondStartDate = secondStart.date;
        LocalTime secondStartTime = secondStart.time;
        LocalDate secondEndDate = secondEnd.date;
        LocalTime secondEndTime = secondEnd.time;

        boolean isFirstStartDateBetweenSecondAppt = isDateBetween(firstStartDate, secondStartDate, secondEndDate);
        boolean isFirstEndDateBetweenSecondAppt = isDateBetween(firstEndDate, secondStartDate, secondEndDate);
        boolean isSecondStartDateBetweenFirstAppt = isDateBetween(secondStartDate, firstStartDate, firstEndDate);
        boolean isSecondEndDateBetweenFirstAppt = isDateBetween(secondEndDate, firstStartDate, firstEndDate);
        if (!(isFirstStartDateBetweenSecondAppt || isFirstEndDateBetweenSecondAppt
                || isSecondStartDateBetweenFirstAppt || isSecondEndDateBetweenFirstAppt)) {
            return false;
        }

        boolean isFirstStartTimeBetweenSecondAppt = isTimeBetween(firstStartTime, secondStartTime, secondEndTime);
        boolean isFirstEndTimeBetweenSecondAppt = isTimeBetween(firstEndTime, secondStartTime, secondEndTime);
        boolean isSecondStartTimeBetweenFirstAppt = isTimeBetween(secondStartTime, firstStartTime, firstEndTime);
        boolean isSecondEndTimeBetweenFirstAppt = isTimeBetween(secondEndTime, firstStartTime, firstEndTime);
        if (!(isFirstStartTimeBetweenSecondAppt || isFirstEndTimeBetweenSecondAppt
                || isSecondStartTimeBetweenFirstAppt || isSecondEndTimeBetweenFirstAppt)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the given date is between the start and the end (inclusive).
     *
     * @param date  Given date to be checked.
     * @param start Start date.
     * @param end   End date.
     * @return True if the given date is between the start and end dates, false otherwise.
     */
    private static boolean isDateBetween(LocalDate date, LocalDate start, LocalDate end) {
        return !(date.isBefore(start) || date.isAfter(end));
    }

    /**
     * Checks if the given time is between the start and the end (inclusive).
     *
     * @param time  Given time to be checked.
     * @param start Start time.
     * @param end   End time.
     * @return True if the given time is between the start and end times, false otherwise.
     */
    private static boolean isTimeBetween(LocalTime time, LocalTime start, LocalTime end) {
        return !(time.isBefore(start) || time.isAfter(end));
    }

    /**
     * Returns the string representation of the formatted date.
     */
    public String getFormattedDate() {
        return date.format(DISPLAYED_DATE_FORMATTER);
    }

    /**
     * Returns the string representation of the formatted time.
     */
    public String getFormattedTime() {
        return time.format(DISPLAYED_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return date.format(DISPLAYED_DATE_FORMATTER) + " " + time.format(DISPLAYED_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof AppointmentDateTime)) { // instanceof handles nulls
            return false;
        }

        AppointmentDateTime apptDateTime = (AppointmentDateTime) other;
        return date.equals(apptDateTime.date) && time.equals(apptDateTime.time);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, time);
    }
}
