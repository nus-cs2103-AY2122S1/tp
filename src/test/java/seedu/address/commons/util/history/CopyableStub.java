package seedu.address.commons.util.history;

import seedu.address.commons.util.Copyable;

/**
 * A stub class that implements the Copyable interface.
 */
public class CopyableStub implements Copyable<CopyableStub> {
    /**
     * Number of times the {@code copy()} method is called;
     */
    private int copyInvocationCount = 0;

    /**
     * Returns a duplicate instance of CopyableStub.
     *
     * @return A duplicate instance of CopyableStub.
     */
    @Override
    public CopyableStub copy() {
        copyInvocationCount++;
        return this;
    }

    /**
     * Resets the number of times {@code copy()} method has been called to 0.
     */
    public void resetCopyInvocationCount() {
        copyInvocationCount = 0;
    }

    /**
     * Gets the number of times {@code copy()} method has been called.
     *
     * @return The number of times {@code copy()} method has been called.
     */
    public int getCopyInvocationCount() {
        return copyInvocationCount;
    }
}
