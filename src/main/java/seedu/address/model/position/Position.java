package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Position in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPositionName(String)}
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS = "Position names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String positionName;

    /**
     * Constructs a {@code Position}.
     *
     * @param positionName A valid position name.
     */
    public Position(String positionName) {
        requireNonNull(positionName);
        checkArgument(isValidPositionName(positionName), MESSAGE_CONSTRAINTS);
        this.positionName = positionName;
    }

    /**
     * Returns true if a given string is a valid position name.
     */
    public static boolean isValidPositionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && positionName.equals(((Position) other).positionName)); // state check
    }

    @Override
    public int hashCode() {
        return positionName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + positionName + ']';
    }

}
