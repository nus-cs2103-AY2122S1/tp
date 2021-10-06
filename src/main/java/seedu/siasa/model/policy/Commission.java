package seedu.siasa.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.AppUtil.checkArgument;

public class Commission {
    public static final String MESSAGE_CONSTRAINTS =
            "Commission should be a number from 0-100 representing the percentage.";


    public final int commissionPercentage;

    public Commission(int percentage) {
        requireNonNull(percentage);
        checkArgument(isValidCommission(percentage), MESSAGE_CONSTRAINTS);
        commissionPercentage = percentage;
    }

    public static boolean isValidCommission(int percentage) { return 0 <= percentage && percentage <= 100; }

    @Override
    public String toString() {
        return Integer.toString(commissionPercentage) + "%";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.siasa.model.policy.Commission // instanceof handles nulls
                && commissionPercentage == ((seedu.siasa.model.policy.Commission) other).commissionPercentage); // state check
    }

    @Override
    public int hashCode() {
        return commissionPercentage;
    }
}
