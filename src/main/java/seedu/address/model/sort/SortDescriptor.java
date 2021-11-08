package seedu.address.model.sort;

import static seedu.address.model.sort.SortFieldType.AMOUNT;
import static seedu.address.model.sort.SortFieldType.DATE;
import static seedu.address.model.sort.SortOrderingType.ASCENDING;
import static seedu.address.model.sort.SortOrderingType.DESCENDING;

import java.util.Comparator;
import java.util.Objects;

import seedu.address.model.order.Order;

/**
 * Encapsulates the sortField and sortOrdering that are used to sort the orderList.
 */
public class SortDescriptor implements Comparator<Order> {
    private final SortField sortField;
    private final SortOrdering sortOrdering;

    /**
     * Constructs a {@code SortDescriptor} with the given SortField and sortOrdering.
     */
    public SortDescriptor(SortField sortField, SortOrdering sortOrdering) {
        this.sortField = sortField;
        this.sortOrdering = sortOrdering;
    }

    /**
     * Returns a success message for the {@code SortDescriptor}.
     */
    public String generateSuccessMessage() {
        return "Sorted by " + sortField + " in " + sortOrdering;
    }

    public SortField getSortField() {
        return sortField;
    }

    public SortOrdering getSortOrdering() {
        return sortOrdering;
    }

    /**
     * Compares the orders {@code o1} and {@code o2} based on the {@code SortField} and {@code sortOrdering}.
     */
    @Override
    public int compare(Order o1, Order o2) {
        SortFieldType fieldType = sortField.getValue();
        SortOrderingType orderingType = sortOrdering.getValue();

        int result = 0;

        if (fieldType.equals(DATE)) {
            result = o1.getDate().compareTo(o2.getDate());
        } else {
            assert(fieldType.equals(AMOUNT));
            result = o1.getAmount().compareTo(o2.getAmount());
        }

        if (orderingType.equals(ASCENDING)) {
            return result;
        } else {
            assert(orderingType.equals(DESCENDING));
            return result * -1;
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortDescriptor)) {
            return false;
        }

        // state check
        SortDescriptor e = (SortDescriptor) other;

        return sortField.equals(e.sortField) && sortOrdering.equals(e.sortOrdering);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortField, sortOrdering);
    }
}
