package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's visit in the address book.
 * Guarantees: immutable; is always valid
 */
public class Visit {
    public static final String MESSAGE_CONSTRAINTS = "Visit date should be of the format yyyy-MM-dd";

    public final String value;

    /**
     * Constructs an {@code Visit}.
     *
     * @param visit A valid visit.
     */
    public Visit(String visit) {
        requireNonNull(visit);
        value = visit;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Visit // instanceof handles nulls
                && value.equals(((Visit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
