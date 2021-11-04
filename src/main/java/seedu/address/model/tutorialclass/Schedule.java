package seedu.address.model.tutorialclass;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
/**
 *
 *
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS =
            "Schedules should follow the format 'day-of-the-week starttime to endtime', "
                    + "and different days must be separated by commas. \n"
                    + "Schedule must contain at least two days\n"
                    + "e.g. Tues 12:00pm to 2:00pm, Friday 12:00pm to 2:00pm";

    private static final String VALIDATION_REGEX = "[,\n]";

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
        return (test.split(VALIDATION_REGEX).length == 2);
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
