package seedu.address.logic.history;

import seedu.address.commons.util.Copyable;

/**
 * A utility class that implements the Copyable interface.
 */
public class CopyableInt implements Copyable<CopyableInt> {
    public static final CopyableInt COPYABLE_ZERO = new CopyableInt(0);
    public static final CopyableInt COPYABLE_ONE = new CopyableInt(1);
    public static final CopyableInt COPYABLE_TWO = new CopyableInt(2);
    public static final CopyableInt COPYABLE_THREE = new CopyableInt(3);
    public static final CopyableInt COPYABLE_FOUR = new CopyableInt(4);

    private final int number;

    protected CopyableInt(int number) {
        this.number = number;
    }

    /**
     * Gets the integer that is saved within the instance of CopyableInt.
     *
     * @return The saved integer.
     */
    protected int getNumber() {
        return number;
    }

    /**
     * Creates a duplicate instance of the CopyableInt.
     *
     * @return The duplicate instance.
     */
    @Override
    public CopyableInt copy() {
        return new CopyableInt(number);
    }
}
