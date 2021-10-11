package seedu.address.model.moduleclass;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;

public class Day {

    public static final String MESSAGE_CONSTRAINTS =
            "Days should only contain numbers";
    public static final String VALIDATION_REGEX = "[1-7]";
    public final DayOfWeek value;

    /**
     * Constructs a {@code Day}
     *
     * @param dayOfWeek the day of the week.
     */
    public Day(String dayOfWeek) {
        requireNonNull(dayOfWeek);
        checkArgument(isValidDay(dayOfWeek), MESSAGE_CONSTRAINTS);
        value = DayOfWeek.of(Integer.parseInt(dayOfWeek));
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }

    public int getDayAsInt() {
        return value.getValue();
    }

    public String getDayAsIntString() {
        return String.valueOf(value.getValue());
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
