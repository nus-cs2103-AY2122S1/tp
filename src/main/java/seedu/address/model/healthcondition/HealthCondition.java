package seedu.address.model.healthcondition;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a HealthCondition in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidHealthCondition(String)}
 */
public class HealthCondition {

    public static final String MESSAGE_CONSTRAINTS = "Health conditions should only contain alphanumeric "
            + "characters and spaces, and it should be between 1 and 50 characters (inclusive)";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String healthCondition;

    /**
     * Constructs a {@code HealthCondition}.
     *
     * @param healthCondition A valid health condition.
     */
    public HealthCondition(String healthCondition) {
        requireNonNull(healthCondition);
        checkArgument(isValidHealthCondition(healthCondition), MESSAGE_CONSTRAINTS);
        this.healthCondition = healthCondition;
    }

    /**
     * Returns true if a given string {@param test} is a valid tag name.
     *
     */
    public static boolean isValidHealthCondition(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 50;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HealthCondition // instanceof handles nulls
                && healthCondition.equals(((HealthCondition) other).healthCondition)); // state check
    }

    @Override
    public int hashCode() {
        return healthCondition.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + healthCondition + ']';
    }

}
