package seedu.modulink.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Mod {

    public static final String MESSAGE_CONSTRAINTS =
            "Please enter a valid Module Code (e.g. CS2103T). You may also include your grouping status.\n"
                + "Available grouping statuses are: need member, need group. Please leave it blank to "
                + "indicate you do not need a group.";
    public static final String VALIDATION_REGEX =
            "([A-Z]|[a-z]){2,3}[0-9]{4}([A-Z]|[a-z])?\\s?[\\p{Alnum}]?[\\p{Alnum} ]*";

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
                && modName.equals(((Mod) other).modName)); // state check
    }

    /**
     * Checks if the statuses of two {@Code Mod} objects are the same.
     *
     * @param other Other object to compare
     * @return boolean to indicate if statuses are the same or not.
     */
    public boolean equalsStatus(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mod // instanceof handles nulls
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
