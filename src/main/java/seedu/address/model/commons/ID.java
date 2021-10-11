package seedu.address.model.commons;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Client's or Product's ID in the address book.
 * Guarantees: immutable; is unique.
 */
public class ID {
    private static int idCounter = 0;
    private final int id;

    /** Constructs a {@code ID}. */
    public ID() {
        id = idCounter;
        idCounter++;
    }
    /**
     * Constructs a {@code ID}.
     *
     * @param idString A valid id.
     */
    public ID(String idString) {
        requireAllNonNull(idString);
        id = Integer.parseInt(idString);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(getId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof ID // instanceof handles nulls
                                   && this.getId() == ((ID) other).getId()); // state check
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
