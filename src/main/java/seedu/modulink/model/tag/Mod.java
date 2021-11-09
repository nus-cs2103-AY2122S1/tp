package seedu.modulink.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.commons.util.AppUtil.checkArgument;

import seedu.modulink.logic.parser.exceptions.ParseException;


/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Mod {

    public static final String MESSAGE_CONSTRAINTS =
            "Please enter a valid Module Code (e.g. CS2103T). A valid Module Code starts with 2-3 letters, followed by"
                + "4 digits, and optionally ends with a letter."
                + " You may also include your grouping status.\n"
                + "Available grouping statuses are: need member, need group. Please leave it blank to "
                + "indicate you do not need a group.";
    public static final String VALIDATION_REGEX =
            "([A-Z]){2,3}[0-9]{4}([A-Z])?";

    public final String oriInput;
    public final String modName;
    public final Status status;

    /**
     * Constructs a {@code Tag}.
     *
     * @param modString A valid tag name.
     */
    public Mod(String modString) throws ParseException {
        requireNonNull(modString);

        this.oriInput = modString;
        int i = modString.indexOf(' ');
        String modCode;


        if (i < 0) {
            modCode = modString.toUpperCase();
        } else {
            modCode = modString.substring(0, i).toUpperCase();
        }

        checkArgument(isValidTagName(modCode), MESSAGE_CONSTRAINTS);


        if (i < 0) {
            this.modName = modCode.toUpperCase();
            this.status = Status.NONE;
        } else {
            this.modName = modCode;
            this.status = Status.parseStatusFromString(modString.substring(i));
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        test = test.toUpperCase();
        int i = test.indexOf(' ');
        String modCode;


        if (i < 0) {
            test = test.toUpperCase();
        } else {
            test = test.substring(0, i).toUpperCase();
        }

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
