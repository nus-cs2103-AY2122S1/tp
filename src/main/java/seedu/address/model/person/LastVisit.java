package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's last visit in the address book.
 * Guarantees: immutable; is always valid
 */
public class LastVisit {
    public final String value;

    /**
     * Constructs an {@code Visit}.
     *
     * @param lastVisit A valid last visit.
     */
    public LastVisit(String lastVisit) {
        requireNonNull(lastVisit);
        value = lastVisit;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                   || (other instanceof LastVisit // instanceof handles nulls
                           && value.equals(((LastVisit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
