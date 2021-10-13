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
                    + "with different classes seperated by commas. \n"
                    + "only contain alphanumeric characters, spaces and -'s"
                    + "between start and end time and should not be blank";

    private static final String TIME_REGEX = "[^(1[0-2]|0?[1-9]):([0-5]?[0-9])(●?[AP]M)?$]";
    private static final String VALIDATION_REGEX = "[\\p{Graph}\\p{Punct}].*";

    public final String value;

    /**
     * Constructor for Schedule class.
     * @param value String containing class schedule.
     */
    public Schedule(String value) {
        requireNonNull(value);
        checkArgument(isValidSchedule(value), MESSAGE_CONSTRAINTS);
        this.value = parseaSchedule(value);
    }

    /**
     * Checks validity of input string.
     *
     * @param test Test string.
     * @return validity of input schedule string.
     */
    public static boolean isValidSchedule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String parseaSchedule(String input) {
        String finalSchedule = "";
        String[] lessonTimings = input.split(",");
        for (String lesson : lessonTimings) {
            finalSchedule += lesson.trim() + "\n";
        }
        return finalSchedule;
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
