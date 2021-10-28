package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

public class Compatability {

    public static final String MESSAGE_CONSTRAINTS = "Rating must be a value between 0 and 100";

    public final Optional<Integer> compatabilityRating;

    /**
     * Constructs a {@code Compatability}.
     *
     * @param compatabilityRating A valid Compatability percentage.
     */
    public Compatability(Integer compatabilityRating) {
        checkArgument(isValidRating(compatabilityRating), MESSAGE_CONSTRAINTS);
        this.compatabilityRating = Optional.ofNullable(compatabilityRating);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidRating(Integer test) {
        if (test == null) {
            return true;
        } else {
            return test >= 0 && test <= 100;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Compatability // instanceof handles nulls
                && compatabilityRating.equals(((Compatability) other).compatabilityRating)); // state check
    }

    @Override
    public int hashCode() {
        return compatabilityRating.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (compatabilityRating.isEmpty()) {
            return "[ - % ]";
        } else {
            return '[' + compatabilityRating.get() + "% ]";
        }
    }
}
