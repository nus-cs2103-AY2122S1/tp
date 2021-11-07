package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's academic level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAcadLevel(String)}.
 */
public class AcadLevel {
    public static final String MESSAGE_CONSTRAINTS = "Academic level should contain a maximum of "
            + "15 alphanumeric characters (including spaces).";

    /*
     * Academic level can only consist of a maximum of 15 alphanumeric characters.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]{0,14}";

    public final String value;

    /**
     * Constructs a {@code AcadLevel}.
     *
     * @param acadLevel A valid academic level.
     */
    public AcadLevel(String acadLevel) {
        requireNonNull(acadLevel);
        checkArgument(isValidAcadLevel(acadLevel), MESSAGE_CONSTRAINTS);
        this.value = acadLevel;
    }

    /**
     * Returns true if a given string is a valid academic level.
     */
    public static boolean isValidAcadLevel(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if academic level is an empty string.
     *
     * @return True if academic level is an empty string.
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
                || (other instanceof AcadLevel // instanceof handles nulls
                && value.equals(((AcadLevel) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
