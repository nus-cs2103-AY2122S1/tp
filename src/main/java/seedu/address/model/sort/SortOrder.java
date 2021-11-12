package seedu.address.model.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;

/**
 * Represents a valid sort order in SportsPA.
 */
public class SortOrder {
    public static final String MESSAGE_CONSTRAINTS =
            "Sort Order should be 'name' or 'tag', and it should not be blank";

    /** All possible sort orders **/
    private static List<String> sortOrders = List.of("name", "tag");

    private String sortOrder;

    /**
     * Constructs a {@code SortOrder}.
     */
    public SortOrder(String sortOrder) {
        requireNonNull(sortOrder);
        checkArgument(isValidSortOrder(sortOrder), MESSAGE_CONSTRAINTS);
        this.sortOrder = sortOrder;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Returns true if given string is a valid sortOrder.
     */
    public static boolean isValidSortOrder(String test) {
        return sortOrders.contains(test.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SortOrder // instance of handles nulls
            && sortOrder.equalsIgnoreCase(((SortOrder) other).sortOrder)); // state check
    }

    @Override
    public String toString() {
        return sortOrder;
    }

    @Override
    public int hashCode() {
        return sortOrder.hashCode();
    }
}
