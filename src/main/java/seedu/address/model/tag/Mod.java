package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Mod {

    public static final String MESSAGE_CONSTRAINTS =
            "Module statuses should only contain alphanumeric characters and spaces";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String oriInput;
    public final String modName;
    public final Status status;

    /**
     * Constructs a {@code Tag}.
     *
     * @param modString A valid tag name.
     */
    public Mod(String modString) {
        requireNonNull(modString);
        checkArgument(isValidTagName(modString), MESSAGE_CONSTRAINTS);

        this.oriInput = modString;
        int i = modString.indexOf(' ');
        if (i < 0) {
            this.modName = modString.toUpperCase();
            this.status = Status.NONE;
        } else {
            this.modName = modString.substring(0, i).toUpperCase();
            this.status = Status.parseStatusFromString(modString.substring(i));
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mod // instanceof handles nulls
                && modName.equals(((Mod) other).modName) // state check
                && status.equals(((Mod) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return modName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + modName + ' ' + status.toString() + ']';
    }

}
