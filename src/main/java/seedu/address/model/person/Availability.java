package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's availability in the address book.
 * Guarantees: immutable; is always valid
 */
public class Availability {

    public static final String[] DAYS = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

    public static final String MESSAGE_CONSTRAINTS =
            "Availability should be given as the abbreviated names of days(mon tue wed thu fri sat sun)";

    public final String values;

    /**
     * Constructs an {@code Availability}.
     *
     * @param availability A valid availability string.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        values = availability;
    }

    /**
     * Returns true if a given availability is valid.
     */
    public static boolean isValidAvailability(List<String> test) {
        if (test.size() == 1) {
            return true;
        }
        List<String> days = Arrays.asList(DAYS);
        for (String day : test) {
            if (!days.contains(day)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(String day) {
        return values.contains(day);
    }

    @Override
    public String toString() {
        return values;
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
