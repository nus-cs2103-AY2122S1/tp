package seedu.address.model.sort;

import static seedu.address.model.sort.SortFieldType.AMOUNT;
import static seedu.address.model.sort.SortFieldType.DATE;
import static seedu.address.model.sort.SortOrderingType.ASCENDING;
import static seedu.address.model.sort.SortOrderingType.DESCENDING;

import java.util.Comparator;

import seedu.address.model.order.Order;

/**
 * Encapsulates the sortField and sortOrdering that are used to sort the orderList.
 */
public class SortDescriptor {
    private SortField sortField;
    private SortOrdering sortOrdering;

    /**
     * Constructs a {@code SortDescriptor} with the given SortField and sortOrdering.
     */
    public SortDescriptor(SortField sortField, SortOrdering sortOrdering) {
        this.sortField = sortField;
        this.sortOrdering = sortOrdering;
    }

    /**
     * Generates a comparator using the sortField and sortOrdering.
     */
    public Comparator<Order> generateComparator() {
        SortFieldType fieldType = sortField.getValue();
        SortOrderingType orderingType = sortOrdering.getValue();

        Comparator<Order> comparator = null;

        if (fieldType.equals(DATE)) {
            comparator = Comparator.comparing(Order::getDate);
        } else if (fieldType.equals(AMOUNT)) {
            comparator = Comparator.comparing(Order::getAmount);
        }

        assert(comparator != null);

        if (orderingType.equals(ASCENDING)) {
            return comparator;
        } else {
            assert(orderingType.equals(DESCENDING));
            return comparator.reversed();
        }

    }

}
