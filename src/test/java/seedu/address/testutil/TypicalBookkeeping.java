package seedu.address.testutil;

import seedu.address.model.BookKeeping;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalBookkeeping {
    private static final Double Revenue = 100.0;
    private static final Double Cost = 50.0;
    private static final Double Profit = 50.0;

    /**
     * Returns a {@code Bookkeeping} with initialised values.
     */
    public static BookKeeping getTypicalBookkeeping() {
        return new BookKeeping(Revenue, Cost);
    }
}
