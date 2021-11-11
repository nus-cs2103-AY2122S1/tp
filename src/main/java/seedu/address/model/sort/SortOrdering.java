package seedu.address.model.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

enum SortOrderingType {
    ASCENDING, DESCENDING
}

/**
 * Represents a sort command's ordering in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortOrder(String)}
 */
public class SortOrdering {

    public static final String MESSAGE_CONSTRAINTS =
            "SortOrder style should only be asc(ending) or desc(ending), and it should not be blank";

    public final SortOrderingType value;

    /**
     * Constructs an {@code SortOrder}.
     *
     * @param ordering A valid ordering.
     */
    public SortOrdering(String ordering) {
        requireNonNull(ordering);
        checkArgument(isValidSortOrder(ordering), MESSAGE_CONSTRAINTS);
        value = getSortOrderType(ordering);
    }

    /**
     * Returns true if a given string is a valid ordering.
     */
    public static boolean isValidSortOrder(String test) {
        String ordering = test.toLowerCase();
        return ordering.equals("asc") || ordering.equals("ascending")
                || ordering.equals("desc") || ordering.equals("descending");
    }

    private SortOrderingType getSortOrderType(String input) {
        String ordering = input.toLowerCase();
        if (ordering.equals("asc") || ordering.equals("ascending")) {
            return SortOrderingType.ASCENDING;
        } else if (ordering.equals("desc") || ordering.equals("descending")) {
            return SortOrderingType.DESCENDING;
        } else {
            return null;
        }
    }

    public SortOrderingType getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.name().toLowerCase() + " order.";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortOrdering // instanceof handles nulls
                && value.name().equals(((SortOrdering) other).value.name())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
