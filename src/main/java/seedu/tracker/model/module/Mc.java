package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Module's MC credits in the module tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidMc(int)}
 */
public class Mc {


    public static final String MESSAGE_CONSTRAINTS = "MC credit should only be an Integer from 1-20.";

    public final int value;

    /**
     * Constructs a dummy object only used by JsonUserInfoStorage and McProgressList.
     * This default constructor shouldn't be used anywhere else.
     */
    public Mc() {
        this.value = 0;
    }

    /**
     * Constructs a {@code Phone}.
     *
     * @param value A valid mc credit.
     */
    public Mc(int value) {
        requireNonNull(value);
        checkArgument(isValidMc(value), MESSAGE_CONSTRAINTS);
        this.value = value;
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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
