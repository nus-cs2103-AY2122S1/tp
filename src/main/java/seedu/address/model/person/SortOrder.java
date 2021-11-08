package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the sorting orders we can apply to sort the supplier list.
 */
public class SortOrder {

    public static final String MESSAGE_CONSTRAINTS = "Sorting order can only be either ascending or descending, "
            + "and it should not be blank";

    private static final String ASCENDING = "a";
    private static final String DESCENDING = "d";

    private final String sortingOrder;

    /**
     * Constructs a {@code SortOrder}.
     *
     * @param sortingOrder A valid sortingOrder.
     */
    public SortOrder(String sortingOrder) {
        requireNonNull(sortingOrder);
        checkArgument(isValidSortBy(sortingOrder), MESSAGE_CONSTRAINTS);
        this.sortingOrder = sortingOrder;
    }

    /**
     * Returns true if a given string is a valid sorting order.
     */
    public static boolean isValidSortBy(String test) {
        switch (test) {
        case ASCENDING:
        case DESCENDING:
            return true;
        default:
            return false;
        }
    }

    public boolean isAscending() {
        return sortingOrder.equals(ASCENDING);
    }

    @Override
    public String toString() {
        return sortingOrder.equals("a") ? "ascending" : "descending";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortOrder // instanceof handles nulls
                && sortingOrder.equals(((SortOrder) other).sortingOrder)); // state check
    }

    @Override
    public int hashCode() {
        return sortingOrder.hashCode();
    }
}
