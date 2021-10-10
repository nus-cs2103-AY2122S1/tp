package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class AcadLevel {
    public static final String MESSAGE_CONSTRAINTS = "Academic level should contain a maximum of "
            + "15 alphanumeric characters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * Valid academic streams include case-insensitive p1 to p6, y1 to y6, s1 to s5 and j1 to j2.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]{0,15}";

    public final String acadLevel;

    /**
     * Constructs a {@code Stream}.
     *
     * @param acadLevel A valid academic stream.
     */
    public AcadLevel(String acadLevel) {
        requireNonNull(acadLevel);
        checkArgument(isValidAcadLevel(acadLevel), MESSAGE_CONSTRAINTS);
        this.acadLevel = acadLevel.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAcadLevel(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if academic stream is an empty string.
     *
     * @return True if academic stream is an empty string.
     */
    public boolean isEmpty() {
        return acadLevel.isEmpty();
    }

    @Override
    public String toString() {
        return acadLevel;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcadLevel // instanceof handles nulls
                && acadLevel.equals(((AcadLevel) other).acadLevel)); // state check
    }

    @Override
    public int hashCode() {
        return acadLevel.hashCode();
    }
}
