package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject implements Comparable<Subject> {

    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Subject}.
     *
     * @param value A valid subject.
     */
    public Subject(String value) {
        requireNonNull(value);
        checkArgument(isValidSubject(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid subject.
     *
     * @param test The string to be tested.
     * @return true if test is a valid subject
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && value.equals(((Subject) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Subject other) {
        return value.compareTo(other.value);
    }

}

