package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class CurrentPlan {
    public final String value;

    /**
     * Constructs an {@code CurrentPlan}.
     *
     * @param  currentPlan valid email address.
     */
    public CurrentPlan(String currentPlan) {
        requireNonNull(currentPlan);
        value = currentPlan;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrentPlan // instanceof handles nulls
                && value.equals(((CurrentPlan) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
