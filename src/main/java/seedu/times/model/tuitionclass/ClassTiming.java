package seedu.times.model.tuitionclass;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.times.logic.parser.exceptions.ParseException;

/**
 * Represents a {@code TuitionClass}'s class timing in the Timestable.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassTiming(String)}
 */
public class ClassTiming implements Comparable<ClassTiming> {

    public static final String MESSAGE_CONSTRAINTS =
            "Class Timing must be in the form DAY HH:MM-HH:MM"
                    + "\nStart time must be earlier than end time, and "
                    + "start and end time has to start at the hour or half hour mark, but it can end at 23:59";

    /*
     * The string has to be in the form DAY HH:MM-HH:MM eg MON 23:59-01:00
     */
    public static final String VALIDATION_REGEX =
            "^(?i)(MON |TUE |WED |THU |FRI |SAT |SUN )([01][0-9]|2[0-3]):[0-5][0-9]-([01][0-9]|2[0-3]):[0-5][0-9]";

    public final String value;

    private final LocalTime startTime;

    private final LocalTime endTime;

    private final String day;

    /**
     * Constructs a {@code ClassTiming}.
     *
     * @param classTiming A valid class timing.
     */
    public ClassTiming(String classTiming) {
        requireNonNull(classTiming);
        checkArgument(isValidClassTiming(classTiming), MESSAGE_CONSTRAINTS);
        value = formatClassTiming(classTiming);
        startTime = getStartTimeFromValue();
        endTime = getEndTimeFromValue();
        day = parseDay();
    }

    /**
     * Gets the endTime from value.
     *
     * @return The end time.
     */
    private LocalTime getStartTimeFromValue() {
        String[] timePart = splitTiming(this.value);
        String startTime = timePart[0];
        return LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Gets the startTime from value.
     *
     * @return The start time.
     */
    private LocalTime getEndTimeFromValue() {
        String[] timePart = splitTiming(this.value);
        String endTime = timePart[1];
        return LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Formats the classTiming day into caps.
     *
     * @param classTiming classTiming where day is going to be changed to caps.
     * @return classTiming with the day in caps.
     */
    private String formatClassTiming(String classTiming) {
        String day = classTiming.split(" ")[0].toUpperCase();
        String timing = classTiming.split(" ")[1];
        return day + " " + timing;
    }

    /**
     * Converts Day from the ClassTiming object into the int representation of the day.
     *
     * @return int representation of the Day of the ClassTiming object
     */
    public int getDayToInt() {
        // TODO: change day to enum
        switch (day.toUpperCase()) {
        case "MON":
            return 1;
        case "TUE":
            return 2;
        case "WED":
            return 3;
        case "THU":
            return 4;
        case "FRI":
            return 5;
        case "SAT":
            return 6;
        case "SUN":
            return 7;
        default:
            return 0;
        }
    }

    /**
     * Compares two ClassTiming objects.
     *
     * @param time ClassTiming being compared to.
     * @return true if this ClassTiming is on an earlier day or has end time earlier than time's
     * start time, otherwise false.
     */
    public boolean isEarlier(ClassTiming time) {
        return this.compareTo(time) < 0;
    }

    /**
     * Sees if the ClassTiming's start time is after the LocalTime given.
     *
     * @param time LocalTime being compared to.
     * @return true if ClassTiming has a start time later than given time otherwise false.
     */
    public boolean isAfter(LocalTime time) {
        return this.getStartTime().isAfter(time);
    }

    /**
     * Parses the class timing string to retrieve the day from the value.
     *
     * @return The Day of the ClassTiming value.
     */
    private String parseDay() {
        String[] classTimingPart = this.value.split(" ");
        return classTimingPart[0];
    }

    /**
     * Splits the class timing string to retrieve the start and end time in String form.
     *
     * @param ct ClassTiming string to be split.
     * @return A String array consisting of the start time at index 0 and end time at index 1.
     */
    private static String[] splitTiming(String ct) {
        String[] ctSplit = ct.split(" ");
        String startEndTime = ctSplit[1];
        return startEndTime.split("-");
    }

    /**
     * Parses the input class timing string to retrieve the Start Time.
     *
     * @param ct ClassTiming string to be parsed.
     * @return The start time of the input string.
     * @throws ParseException if ct does not start at the hour or half hour mark.
     */
    private static LocalTime parseStartTime(String ct) throws ParseException {
        String[] timePart = splitTiming(ct);
        String startTime = timePart[0];
        String startTimeMinutes = startTime.split(":")[1];

        if (!startTimeMinutes.equals("00") && !startTimeMinutes.equals("30")) {
            throw new ParseException("Invalid start time");
        }

        return LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Parses the input class timing string to retrieve the End Time.
     *
     * @param ct ClassTiming string to be parsed.
     * @return The end time of the input string.
     * @throws ParseException if ct does not start at the hour or half hour mark.
     */
    private static LocalTime parseEndTime(String ct) throws ParseException {
        String[] timePart = splitTiming(ct);
        String endTime = timePart[1];
        String endTimeMinutes = endTime.split(":")[1];

        if (!endTimeMinutes.equals("00") && !endTimeMinutes.equals("30") && !endTime.equals("23:59")) {
            throw new ParseException("Invalid end time");
        }

        return LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Gets the start time of the class timing.
     *
     * @return Start Time of ClassTiming.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the class timing.
     *
     * @return End Time of ClassTiming.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the day of the class timing.
     *
     * @return Day of ClassTiming.
     */
    private String getDay() {
        return this.day;
    }

    /**
     * Returns boolean true if other is on the same day as this, false otherwise.
     *
     * @param other ClassTiming to compare to this.
     * @return Boolean on whether this and other is on the same day.
     */
    public Boolean isSameDay(ClassTiming other) {
        if (this.getDayToInt() == other.getDayToInt()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the timing string without the day of the ClassTiming.
     *
     * @return Timing of the ClassTiming without the Day.
     */
    public String getClassTiming() {
        return this.value.split(" ")[1];
    }

    /**
     * Returns true if a given string is a valid class timing.
     */
    public static boolean isValidClassTiming(String test) {
        try {
            if (test.matches(VALIDATION_REGEX)) {
                LocalTime startTime = parseStartTime(test);
                LocalTime endTime = parseEndTime(test);
                return startTime.isBefore(endTime);
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassTiming // instanceof handles nulls
                && value.equals(((ClassTiming) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(ClassTiming o) {
        int thisDayInt = getDayToInt();

        String otherDay = o.getDay();
        int otherDayInt = o.getDayToInt();
        LocalTime otherStartTime = o.getStartTime();

        if (thisDayInt < otherDayInt) {
            return -1;
        } else if (otherDayInt < thisDayInt) {
            return 1;
        } else if (this.getStartTime().equals(otherStartTime)) {
            return 0;
        } else {
            return otherStartTime.isAfter(this.getEndTime()) || this.getStartTime().isBefore(otherStartTime) ? -1 : 1;
        }
    }
}
