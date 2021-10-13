package seedu.address.model.tuition;

import static java.util.Objects.requireNonNull;

/**
 * Represent the limit of the participant
 */
public class ClassLimit {
    public final int limit;

    /**
     * Constructor for class limit.
     *
     * @param limit
     */
    public ClassLimit(int limit) {
        requireNonNull(limit);
        this.limit = limit;
    }

    @Override
    public String toString() {
        return limit + "";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassLimit // instanceof handles nulls
                && limit == ((ClassLimit) other).limit); // state check
    }


    public int getLimit() {
        return limit;
    }

    /**
     * checks whether the class limit is valid(limit should be larger than 0)
     * @param myLimit the limit to be checked
     * @return true if the limit is valid.
     */
    public static boolean isValid(int myLimit) {
        return myLimit > 0;
    }
}
