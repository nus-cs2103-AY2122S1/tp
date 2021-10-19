package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class LinkYear {
    public static final String MESSAGE_CONSTRAINTS =
            "Years should contain only alphanumeric characters, underscores, "
                    + "tilde, dots and dashes, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}._\\-~][\\p{Alnum}._\\-~]*";

    public final String year;

    /**
     * Constructs a {@code LinkYear}.
     *
     * @param year A valid academic year to be used in the link.
     */
    public LinkYear(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        this.year = year;
    }

    public LinkYear() {
        this.year = null;
    }

    public boolean isNull() {
        return year == null ? true : false;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return year;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkYear // instanceof handles nulls
                && ((isNull() && ((LinkYear) other).isNull())
                || year.equals(((LinkYear) other).year))); // state check
    }

    @Override
    public int hashCode() {
        return isNull() ? 0 : year.hashCode();
    }
}
