package seedu.address.model.Tuition;

import static java.util.Objects.requireNonNull;

/**
 * Represent the limit of the participant
 */
public class ClassLimit {
    public final int limit;

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
}
