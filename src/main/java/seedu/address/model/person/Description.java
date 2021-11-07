package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's description / remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description should not be more than 500 characters long";

    public final String value;

    /**
     * Public constructor to create remark
     */
    public Description(String desc) {
        requireNonNull(desc);
        value = desc;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Description
                && value.equals(((Description) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if a given string is a valid Description.
     */
    public static boolean isValidDescription(String test) {
        return !(test.length() > 500);
    }
}
