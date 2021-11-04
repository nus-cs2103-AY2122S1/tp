package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

public class Compatibility {

    public static final String MESSAGE_CONSTRAINTS = "Rating must be a value between 0 and 100";

    public final Optional<Integer> compatibilityRating;

    /**
     * Constructs a {@code Compatibility}.
     *
     * @param compatibilityRating A valid Compatibility percentage.
     */
    public Compatibility(Integer compatibilityRating) {
        checkArgument(isValidRating(compatibilityRating), MESSAGE_CONSTRAINTS);
        this.compatibilityRating = Optional.ofNullable(compatibilityRating);
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
                || (other instanceof Compatibility // instanceof handles nulls
                && compatibilityRating.equals(((Compatibility) other).compatibilityRating)); // state check
    }

    @Override
    public int hashCode() {
        return compatibilityRating.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (compatibilityRating.isEmpty()) {
            return " - ";
        } else {
            return compatibilityRating.get() + "%";
        }
    }
}
