package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's or Product's ID in the address book.
 * Guarantees: immutable; is unique.
 */
public class ID {
    public static final String MESSAGE_CONSTRAINTS = "ID should only contain numbers and it should not be blank";

    // The quantity should contain digits from 0 to 9 only.
    public static final String VALIDATION_REGEX = "[0-9]+";

    private static int clientIDCounter = 1;
    private static int productIDCounter = 1;

    private final int id;

    public ID(int id) {
        this.id = id;
    }

    /**
     * Constructs a {@code ID} with the given {@code id}.
     *
     * @param id id
     */
    public ID(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = Integer.parseInt(id);
    }

    /**
     * Constructs a {@code ID} for {@code Client}.
     */
    public static ID getNewClientID() {
        return new ID(clientIDCounter++);
    }

    /**
     * Constructs a {@code ID} for {@code Product}.
     */
    public static ID getNewProductID() {
        return new ID(productIDCounter++);
    }

    public int getId() {
        return id;
    }

    /**
     * Returns true if a given string is a valid id.
     *
     * @param id ID to be tested.
     */
    public static boolean isValidId(String id) {
        return id.matches(VALIDATION_REGEX);
    }

    /**
     * Resets the counter. To be used with clear command.
     */
    public static void resetIdCounter() {
        clientIDCounter = 1;
        productIDCounter = 1;
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
