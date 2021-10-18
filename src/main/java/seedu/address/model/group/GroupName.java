package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Group's name in tApp.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class GroupName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only an alphabet followed by 2 digits a - and another digit";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z]\\d{2}[-]\\d";

    public final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public GroupName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupName // instanceof handles nulls
                && name.equals(((GroupName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
