package seedu.address.model.CurrentPlans;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidPlanName(String)}
 */
public class CurrentPlans {

    public static final String MESSAGE_CONSTRAINTS = "Financial plan names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String planName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param planName A valid tag name.
     */
    public CurrentPlans(String planName) {
        requireNonNull(planName);
        checkArgument(isValidPlanName(planName), MESSAGE_CONSTRAINTS);
        this.planName = planName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidPlanName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrentPlans // instanceof handles nulls
                && planName.equals(((CurrentPlans) other).planName)); // state check
    }

    @Override
    public int hashCode() {
        return planName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + planName + ']';
    }

}
