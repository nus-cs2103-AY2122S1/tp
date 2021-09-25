package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's academic stream in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAcadStream(String)}
 */
public class AcadStream {
    public static final String MESSAGE_CONSTRAINTS =
            "Academic stream should only contain alphabetic characters and spaces.";

    /*
     * The first character of the academic stream must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String acadStream;

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
        this.acadStream = acadStream;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAcadStream(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if academic stream is empty.
     *
     * @return True if academic stream is empty.
     */
    public boolean isEmpty() {
        return acadStream.isEmpty();
    }

    @Override
    public String toString() {
        return acadStream;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcadStream // instanceof handles nulls
                && acadStream.equals(((AcadStream) other).acadStream)); // state check
    }

    @Override
    public int hashCode() {
        return acadStream.hashCode();
    }

}
