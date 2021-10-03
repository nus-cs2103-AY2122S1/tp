package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's MC credits in the module tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidMc(int)}
 */
public class Mc {


    public static final String MESSAGE_CONSTRAINTS =
            "MC credit should only be an Integer, and it should not be less than 0.";
    public final int value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param mc A valid mc credit.
     */
    public Mc(int mc) {
        requireNonNull(mc);
        checkArgument(isValidMc(mc), MESSAGE_CONSTRAINTS);
        this.value = mc;
    }

    /**
     * Returns true if a given string is a valid mc credit.
     */
    public static boolean isValidMc(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return value + "MC(s)";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.tracker.model.module.Mc // instanceof handles nulls
                && value == (((seedu.tracker.model.module.Mc) other).value)); // state check
    }
}
