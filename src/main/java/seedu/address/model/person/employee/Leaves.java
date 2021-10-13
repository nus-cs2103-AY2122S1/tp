package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's number of leaves in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLeaves(String)}
 */
public class Leaves {
    public static final String MESSAGE_CONSTRAINTS =
            "Leaves should only be in numbers";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

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
        return test.matches(VALIDATION_REGEX);
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
