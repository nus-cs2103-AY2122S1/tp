package seedu.address.logic.history;

import seedu.address.commons.util.Copyable;

public class CopyableInt implements Copyable<CopyableInt> {
    private final int number;

    protected CopyableInt(int number) {
        this.number = number;
    }

    protected int getNumber() {
        return number;
    }

    @Override
    public CopyableInt copy() {
        return new CopyableInt(number);
    }
}
