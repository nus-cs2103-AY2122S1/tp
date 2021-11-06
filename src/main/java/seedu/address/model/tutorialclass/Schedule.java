package seedu.address.model.tutorialclass;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
/**
 *
 *
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS =
            "Schedules should follow the format 'day-of-the-week starttime[(hh:mm)am/pm] to endtime[(hh:mm)am/pm]', "
                    + "and different days must be separated by commas. \n"
                    + "Schedule must contain exactly two days\n"
                    + "e.g. Tues 12:00pm to 2:00pm, Friday 12:00pm to 2:00pm";

    private static final String SPLIT_REGEX = "[,\n]";
    private static final String TIME_REGEX = "((1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm]))";
    private static final String DAY_OF_THE_WEEK_REGEX = "((Mon|Tues|Wed(nes)?|Thur(s)?|Fri|Sat(ur)?|Sun)(day)?)";

    private static final String VALIDATION_REGEX = DAY_OF_THE_WEEK_REGEX + " " + TIME_REGEX + " to " + TIME_REGEX;

    public final String value;

    /**
     * Constructor for Schedule class.
     * @param value String containing class schedule.
     */
    public Schedule(String value) {
        requireNonNull(value);
        checkArgument(isValidSchedule(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Checks validity of input string.
     *
     * @param test Test string.
     * @return validity of input schedule string.
     */
    public static boolean isValidSchedule(String test) {
        String[] timeslots = test.split(SPLIT_REGEX);
        if (timeslots.length != 2) {
            return false;
        }
        for (String timeslot : timeslots) {
            if (!timeslot.trim().matches(VALIDATION_REGEX)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && value.equals(((Schedule) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
