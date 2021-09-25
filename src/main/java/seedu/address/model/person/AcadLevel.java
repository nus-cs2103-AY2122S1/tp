package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class AcadLevel {
    public static final String MESSAGE_CONSTRAINTS = "Academic level should only contain one case-insensitive "
            + "alphabetic (P/S/J/Y) followed by one numeric characters (1-6 for P/Y; 1-5 for S; 1-2 for J).";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(([pyPY][1-6][\\s]*)|([sS][1-5][\\s]*)|([jJ][1-2][\\s]*))";

    public final String acadLevel;

    /**
     * Constructs a {@code Stream}.
     *
     * @param acadLevel A valid academic stream.
     */
    public AcadLevel(String acadLevel) {
        requireNonNull(acadLevel);
        if (!acadLevel.isEmpty()) {
            checkArgument(isValidAcadLevel(acadLevel), MESSAGE_CONSTRAINTS);
        }
        this.acadLevel = acadLevel.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAcadLevel(String test) {
        return test.matches(VALIDATION_REGEX);
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
