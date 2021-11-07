package seedu.address.testutil;

import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.sort.SortField;
import seedu.address.model.sort.SortOrdering;

/**
 * A utility class to help with building Sort objects.
 */
public class SortDescriptorBuilder {
    public static final SortField DATE_FIELD = new SortField("date");
    public static final SortField AMOUNT_FIELD = new SortField("amount");
    public static final SortOrdering ASC_ORDER = new SortOrdering("ascending");
    public static final SortOrdering DESC_ORDER = new SortOrdering("descending");

    private SortField sortField;
    private SortOrdering sortOrdering;


    /**
     * Creates a {@code SortDescriptorBuilder} with the empty fields.
     */
    public SortDescriptorBuilder() {
        sortField = null;
        sortOrdering = null;
    }

    /**
     * Initializes the SortDescriptorBuilder with the data of {@code sortDescriptorToCopy}.
     */
    public SortDescriptorBuilder(SortDescriptor sortDescriptorToCopy) {
        sortField = sortDescriptorToCopy.getSortField();
        sortOrdering = sortDescriptorToCopy.getSortOrdering();
    }

    /**
     * Initializes the {@code sortField} of the {@code SortDescriptor} that we are building to {@code DATE_FIELD}.
     */
    public SortDescriptorBuilder onDateField() {
        this.sortField = DATE_FIELD;
        return this;
    }

    /**
     * Initializes the {@code sortField} of the {@code SortDescriptor} that we are building to {@code AMOUNT_FIELD}.
     */
    public SortDescriptorBuilder onAmountField() {
        this.sortField = AMOUNT_FIELD;
        return this;
    }

    /**
     * Initializes the {@code SortOrdering} of the {@code SortDescriptor} that we are building to {@code ASC_ORDER}.
     */
    public SortDescriptorBuilder inAscOrder() {
        this.sortOrdering = ASC_ORDER;
        return this;
    }

    /**
     * Initializes the {@code SortOrdering} of the {@code SortDescriptor} that we are building to {@code DESC_ORDER}.
     */
    public SortDescriptorBuilder inDescOrder() {
        this.sortOrdering = DESC_ORDER;
        return this;
    }

    public SortDescriptor build() {
        return new SortDescriptor(sortField, sortOrdering);
    }

}
