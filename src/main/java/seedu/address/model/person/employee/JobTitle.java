package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's job title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJobTitle(String)}
 */
public class JobTitle {
    public static final String MESSAGE_CONSTRAINTS =
            "job titles should only contain alphanumeric characters and spaces, and should not be left blank";

    /*
     * The first character of the job title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String jobTitle;

    /**
     * Constructs a {@code job title}.
     *
     * @param title A valid job title.
     */
    public JobTitle(String title) {
        requireNonNull(title);
        checkArgument(isValidJobTitle(title), MESSAGE_CONSTRAINTS);
        jobTitle = title;
    }

    /**
     * Returns true if a given string is a valid leaves input.
     */
    public static boolean isValidJobTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return jobTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitle // instanceof handles nulls
                && jobTitle.equals(((JobTitle) other).jobTitle)); // state check
    }

    @Override
    public int hashCode() {
        return jobTitle.hashCode();
    }
}
