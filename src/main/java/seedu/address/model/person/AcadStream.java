package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's academic stream in the address book.
 * Guarantees: immutable; no constraints for academic stream description.
 */
public class AcadStream {

    public final String value;

    /**
     * Constructs a {@code AcadStream}.
     *
     * @param acadStream A valid academic stream.
     */
    public AcadStream(String acadStream) {
        requireNonNull(acadStream);
        value = acadStream;
    }

    /**
     * Returns true if academic stream is empty.
     *
     * @return True if academic stream is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcadStream // instanceof handles nulls
                && value.equals(((AcadStream) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
