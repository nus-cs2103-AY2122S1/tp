package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's number of leaves in RHRH.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeaves(String)}
 */
public class Leaves {
    public static final String MESSAGE_CONSTRAINTS =
            "Leaves should be numerical and be more than or equals to 0, less than or equals to 365";

    public final String currentLeaves;

    /**
     * Constructs a {@code Leave page}.
     *
     * @param leaves A valid leaves count.
     */
    public Leaves(String leaves) {
        requireNonNull(leaves);
        checkArgument(isValidLeaves(leaves), MESSAGE_CONSTRAINTS);
        currentLeaves = leaves;
    }

    /**
     * Returns true if a given string is a valid leaves input.
     */
    public static boolean isValidLeaves(String test) {
        try {
            return Integer.parseInt(test) >= 0 && Integer.parseInt(test) <= 365;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return currentLeaves;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Leaves // instanceof handles nulls
                && currentLeaves.equals(((Leaves) other).currentLeaves)); // state check
    }

    @Override
    public int hashCode() {
        return currentLeaves.hashCode();
    }
}
