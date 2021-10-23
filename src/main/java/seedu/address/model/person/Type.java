package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Type ie Student or Tutor in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {

    public static final String MESSAGE_CONSTRAINTS = "Type must be valid ie Student or Tutor, and it should not be "
            + "blank";

    /*
     * Regex for the Type.
     */
    public static final String VALIDATION_REGEX = "student|tutor";

    public final String value;

    /**
     * Constructs an {@code Type}.
     *
     * @param type A valid Type ie Student or Tutor.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = type;
    }

    /**
     * Returns true if a given string is a valid Type.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Type)) {
            return false;
        }

        Type otherType = (Type) other;

        return value.equals(otherType.value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
