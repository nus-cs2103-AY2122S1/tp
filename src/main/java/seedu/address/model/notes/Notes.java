package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;

/**
 * Represents the notes about an applicant in the address book.
 */
public class Notes {

    public static final Notes EMPTY_NOTES = new Notes("");

    public final String information;

    /**
     * Constructs a {@code Notes}.
     *
     * @param information A valid notes.
     */
    public Notes(String information) {
        requireNonNull(information);
        this.information = information;
    }

    @Override
    public String toString() {
        return information;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && information.equals(((Notes) other).information)); // state check
    }

    @Override
    public int hashCode() {
        return information.hashCode();
    }

}
