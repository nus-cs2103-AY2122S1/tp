package seedu.address.testutil;

import seedu.address.model.BookKeeping;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalBookkeeping {

    private TypicalBookkeeping() {} // prevents instantiation

    /**
     * Returns a {@code Bookkeeping} with initialised values.
     */
    public static BookKeeping getTypicalBookkeeping() {
        BookKeeping bookKeeping = new BookKeeping();
        bookKeeping.addRevenue(100.0);
        bookKeeping.addCost(50.0);
        return bookKeeping;
    }
}
