package seedu.address.testutil;

import seedu.address.model.BookKeeping;

/**
 * A utility class containing the revenue,cost and profits {@code Bookkeeping} objects to be used in tests.
 */
public class TypicalBookkeeping {
    public static final Double REVENUE = 966.0;
    public static final Double COST = 105.0;
    public static final Double PROFIT = 219.8;

    public static BookKeeping getTypicalBookkeeping() {
        return new BookKeeping(TypicalBookkeeping.REVENUE, TypicalBookkeeping.COST,
                TypicalBookkeeping.PROFIT);
    }

}
