package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Represents a Person's availability in the address book.
 * Guarantees: immutable; is always valid
 */
public class Availability {

    public static final String MESSAGE_CONSTRAINTS =
            "Availability should be given as a list of numbers separated by a space each, "
            + "where 1 represents Monday, 2 represents Tuesday... and 7 represents Sunday";

    public final List<DayOfWeek> values;

    /**
     * Constructs an {@code Availability}.
     *
     * @param availability A valid availability string.
     */
    public Availability(List<DayOfWeek> availability) {
        requireNonNull(availability);
        values = availability;
    }

    /**
     * Returns true if a given availability list is valid.
     */
    public static boolean isValidAvailability(List<String> test) {
        try {
            if (test.size() <= 1) { // empty but valid
                return true;
            }
            for (String s : test) {
                int dayNumber = Integer.parseInt(s);

                if (dayNumber < 1 || dayNumber > 7) {
                    return false;
                }
            }
        } catch (NumberFormatException e) { // if any of the string is not a number, it is invalid
            return false;
        }
        return true;
    }

    public boolean contains(DayOfWeek day) {
        return values.contains(day);
    }

    @Override
    public String toString() {
        return values.stream().map(dayOfWeek -> dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Availability // instanceof handles nulls
                && values.equals(((Availability) other).values)); // state check
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }
}
