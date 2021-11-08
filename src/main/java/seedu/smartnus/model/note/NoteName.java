package seedu.smartnus.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a Question's name in SmartNus.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class NoteName {

    public static final String MESSAGE_CONSTRAINTS = MESSAGE_INVALID_COMMAND_FORMAT + "Note can take any values,\n"
            + "but it should not be blank or start with a whitespace";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public NoteName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteName // instanceof handles nulls
                && fullName.equals(((NoteName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
