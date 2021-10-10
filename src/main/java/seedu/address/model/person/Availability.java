package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's availability in the address book.
 * Guarantees: immutable; is always valid
 */
public class Availability {

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
