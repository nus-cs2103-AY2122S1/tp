package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's academic stream in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAcadStream(String)}
 */
public class AcadStream {
    public static final String MESSAGE_CONSTRAINTS = "Academic stream should only contain alphanumeric characters, "
            + "and spaces.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * Academic stream can be any alphabetic character with or without space.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Stream}.
     *
     * @param acadStream A valid academic stream.
     */
    public AcadStream(String acadStream) {
        requireNonNull(acadStream);
        if (!acadStream.isEmpty()) {
            checkArgument(isValidAcadStream(acadStream), MESSAGE_CONSTRAINTS);
        }
        value = acadStream;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAcadStream(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
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
