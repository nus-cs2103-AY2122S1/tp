package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's school name in the address book.
 * Guarantees: immutable; no constraints for school name.
 */
public class School {

    public final String value;

    /**
     * Constructs a {@code School}.
     *
     * @param schoolName A valid school name.
     */
    public School(String schoolName) {
        requireNonNull(schoolName);
        value = schoolName;
    }

    /**
     * Returns true if school name is empty.
     *
     * @return True if school name is empty.
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
                || (other instanceof School // instanceof handles nulls
                && value.equals(((School) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
