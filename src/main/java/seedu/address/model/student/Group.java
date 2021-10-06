package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Group {

    public static final String MESSAGE_CONSTRAINTS =
            "Group name should be in AXXA format, where A can be any alphabet and X can be any numerical number";
    public static final String VALIDATION_REGEX = "[A-Za-z]\\d{2}[A-Za-z]";

    // Group name
    public final String value;

    /**
     * Constructs an {@code Group}.
     *
     * @param name A valid Group name.
     */
    public Group(String name) {
        requireNonNull(name);
        checkArgument(isValidGroup(name), MESSAGE_CONSTRAINTS);
        value = reformatGroup(name);
    }

    /**
     * Returns true if a given string is a valid Group name.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Reformats valid Group name to uppercase.
     */
    public static String reformatGroup(String name) {
        assert name.length() == 4; // Group name should already be validated
        char type = Character.toUpperCase(name.charAt(0));
        return type + name.substring(1, 3) + Character.toUpperCase(name.charAt(3));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && value.equals(((Group) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
